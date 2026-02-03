package com.gps.tracking.service;

import com.gps.tracking.dto.CreateAssignmentDTO;
import com.gps.tracking.dto.CreateMonitoringDTO;
import com.gps.tracking.entity.Assignment;
import com.gps.tracking.entity.Monitoring;
import com.gps.tracking.kafka.GpsEvent;
import com.gps.tracking.kafka.GpsEventProducer;
import com.gps.tracking.repository.AssignmentRepository;
import com.gps.tracking.repository.MonitoringRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class MonitoringService {

    @Inject
    private AssignmentRepository assignmentRepository;
    @Inject
    private MonitoringRepository monitoringRepository;
    @Inject
    private GpsEventProducer gpsEventProducer;

    @Transactional
    public Uni<Assignment> createAssignment(CreateAssignmentDTO dto) {

        Assignment a = new Assignment();
        a.deviceId = dto.deviceId;
        a.vehicleId = dto.vehicleId;
        a.userId = dto.userId;

        return assignmentRepository.persist(a)
                .onItem().transform(v -> a);
    }

    public Uni<List<Assignment>> listAssignments() {
        return assignmentRepository.listAll();
    }

    @Transactional
    public Uni<Monitoring> createMonitoring(CreateMonitoringDTO dto) {

        if (dto.assignmentId == null) {
            return Uni.createFrom()
                    .failure(new IllegalArgumentException("assignmentId requerido"));
        }

        return assignmentRepository.findById(dto.assignmentId)
                .onItem().ifNull().failWith(
                        new IllegalArgumentException("Asignación no existe")
                )
                .flatMap(assignment -> {
                    Monitoring m = new Monitoring();
                    m.assignmentId = dto.assignmentId;
                    m.latitud = dto.latitud;
                    m.longitud = dto.longitud;
                    m.velocidad = dto.velocidad;
                    m.fecha = LocalDateTime.now();
                    return monitoringRepository.persist(m)
                            .onItem().transform(v -> m)
                            .replaceWith(m)
                            .flatMap(saved ->
                                    maybeSendGpsEvent(saved, assignment)
                                            .replaceWith(saved)
                            );
                });
    }

    public Uni<List<Monitoring>> listMonitoring() {
        return monitoringRepository.listAll();
    }

    private Uni<Boolean> maybeSendGpsEvent(Monitoring m, Assignment assignment) {

        if (m.velocidad == null || m.velocidad.compareTo(BigDecimal.valueOf(80)) <= 0) {
            return Uni.createFrom().item(false);
        }

        GpsEvent event = new GpsEvent();
        event.deviceId = assignment.deviceId;
        event.vehicleId = assignment.vehicleId;
        event.latitud = m.latitud;
        event.longitud = m.longitud;
        event.velocidad = m.velocidad;

        return gpsEventProducer.send(event);
    }
}
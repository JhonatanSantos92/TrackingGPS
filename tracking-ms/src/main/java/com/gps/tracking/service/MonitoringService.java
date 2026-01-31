package com.gps.tracking.service;

import com.gps.tracking.dto.CreateAssignmentDTO;
import com.gps.tracking.dto.CreateMonitoringDTO;
import com.gps.tracking.entity.Assignment;
import com.gps.tracking.entity.Monitoring;
import com.gps.tracking.kafka.GpsEvent;
import com.gps.tracking.kafka.GpsEventProducer;
import com.gps.tracking.repository.AssignmentRepository;
import com.gps.tracking.repository.MonitoringRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class MonitoringService {

    private final AssignmentRepository assignmentRepository;
    private final MonitoringRepository monitoringRepository;
    private final GpsEventProducer gpsEventProducer;

    public MonitoringService(AssignmentRepository assignmentRepository,
                             MonitoringRepository monitoringRepository,
                             GpsEventProducer gpsEventProducer) {
        this.assignmentRepository = assignmentRepository;
        this.monitoringRepository = monitoringRepository;
        this.gpsEventProducer = gpsEventProducer;
    }

    @Transactional
    public Assignment createAssignment(CreateAssignmentDTO dto) {
        Assignment a = new Assignment();
        a.deviceId = dto.deviceId;
        a.vehicleId = dto.vehicleId;
        a.userId = dto.userId;
        assignmentRepository.persist(a);
        return a;
    }

    public List<Assignment> listAssignments() {
        return assignmentRepository.listAll();
    }

    @Transactional
    public Monitoring createMonitoring(CreateMonitoringDTO dto) {

        if (dto.assignmentId == null) {
            throw new IllegalArgumentException("assignmentId requerido");
        }

        var assignment = assignmentRepository.findById(dto.assignmentId);
        if (assignment == null) {
            throw new IllegalArgumentException("Asignación no existe");
        }

        Monitoring m = new Monitoring();
        m.assignmentId = dto.assignmentId;
        m.latitud = dto.latitud;
        m.longitud = dto.longitud;
        m.velocidad = dto.velocidad;
        m.fecha = java.time.LocalDateTime.now();

        monitoringRepository.persist(m);

        if(m.velocidad != null && m.velocidad.compareTo(BigDecimal.valueOf(80)) > 0) {
            GpsEvent event = new GpsEvent();
            event.deviceId = assignment.deviceId;
            event.vehicleId = assignment.vehicleId;
            event.latitud = m.latitud;
            event.longitud = m.longitud;
            event.velocidad = m.velocidad;
            try {
                gpsEventProducer.send(event);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error al enviar evento GPS: " + e.getMessage());
            }
        }
        return m;
    }

    public List<Monitoring> listMonitoring() {
        return monitoringRepository.listAll();
    }
}
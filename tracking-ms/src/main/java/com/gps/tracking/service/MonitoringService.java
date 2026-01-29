package com.gps.tracking.service;

import com.gps.tracking.dto.CreateAssignmentDTO;
import com.gps.tracking.dto.CreateMonitoringDTO;
import com.gps.tracking.entity.Assignment;
import com.gps.tracking.entity.Monitoring;
import com.gps.tracking.repository.AssignmentRepository;
import com.gps.tracking.repository.MonitoringRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MonitoringService {

    private final AssignmentRepository assignmentRepository;
    private final MonitoringRepository monitoringRepository;

    public MonitoringService(AssignmentRepository assignmentRepository,
                             MonitoringRepository monitoringRepository) {
        this.assignmentRepository = assignmentRepository;
        this.monitoringRepository = monitoringRepository;
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
        Monitoring m = new Monitoring();
        m.assignmentId = dto.assignmentId;
        m.latitud = dto.latitud;
        m.longitud = dto.longitud;
        m.velocidad = dto.velocidad;
        m.fecha = java.time.LocalDateTime.now();
        monitoringRepository.persist(m);
        return m;
    }

    public List<Monitoring> listMonitoring() {
        return monitoringRepository.listAll();
    }
}
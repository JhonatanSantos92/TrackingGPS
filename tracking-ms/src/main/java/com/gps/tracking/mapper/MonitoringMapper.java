package com.gps.tracking.mapper;

import com.gps.tracking.dto.CreateMonitoringDTO;
import com.gps.tracking.dto.MonitoringDTO;
import com.gps.tracking.entity.Monitoring;

import java.time.LocalDateTime;

public class MonitoringMapper {

    public static Monitoring toEntity(CreateMonitoringDTO dto) {
        Monitoring m = new Monitoring();
        m.assignmentId = dto.assignmentId;
        m.latitud = dto.latitud;
        m.longitud = dto.longitud;
        m.velocidad = dto.velocidad;
        m.fecha = LocalDateTime.now();
        return m;
    }

    public static MonitoringDTO toDTO(Monitoring m) {
        MonitoringDTO dto = new MonitoringDTO();
        dto.id = m.id;
        dto.assignmentId = m.assignmentId;
        dto.latitud = m.latitud;
        dto.longitud = m.longitud;
        dto.velocidad = m.velocidad;
        dto.fecha = m.fecha;
        return dto;
    }
}

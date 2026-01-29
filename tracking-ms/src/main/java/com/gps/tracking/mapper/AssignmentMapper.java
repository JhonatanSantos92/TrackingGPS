package com.gps.tracking.mapper;

import com.gps.tracking.dto.AssignmentDTO;
import com.gps.tracking.dto.CreateAssignmentDTO;
import com.gps.tracking.entity.Assignment;

import java.time.LocalDateTime;

public class AssignmentMapper {

    public static Assignment toEntity(CreateAssignmentDTO dto) {
        Assignment a = new Assignment();
        a.deviceId = dto.deviceId;
        a.vehicleId = dto.vehicleId;
        a.userId = dto.userId;
        a.fecha_creacion = LocalDateTime.now();
        return a;
    }

    public static AssignmentDTO toDTO(Assignment a) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.id = a.id;
        dto.deviceId = a.deviceId;
        dto.vehicleId = a.vehicleId;
        dto.userId = a.userId;
        return dto;
    }
}

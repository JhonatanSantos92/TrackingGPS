package com.gps.tracking.mapper;

import com.gps.tracking.dto.*;

public class TrackingMapper {

    public static TrackingResponseDTO map(
            MonitoringDTO m,
            DeviceDTO device,
            VehicleDTO vehicle,
            UserDTO user
    ) {
        TrackingResponseDTO dto = new TrackingResponseDTO();

        dto.latitud = m.latitud;
        dto.longitud = m.longitud;
        dto.velocidad = m.velocidad;
        dto.fecha = m.fecha;

        dto.device = device;
        dto.vehicle = vehicle;
        dto.user = user;

        return dto;
    }
}
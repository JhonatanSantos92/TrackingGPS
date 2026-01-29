package com.gps.tracking.service;

import com.gps.tracking.dto.*;
import com.gps.tracking.mapper.TrackingMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TrackingService {

    @Inject DeviceService deviceService;
    @Inject VehicleService vehicleService;
    @Inject UserService userService;

    public TrackingResponseDTO buildTracking(
            MonitoringDTO monitoring,
            AssignmentDTO assignment
    ) {

        DeviceDTO device = deviceService.getById(assignment.deviceId.longValue());
        VehicleDTO vehicle = vehicleService.getById(assignment.vehicleId.longValue());

        UserDTO user = null;
        if (assignment.userId != null) {
            user = userService.getById(assignment.userId);
        }

        return TrackingMapper.map(monitoring, device, vehicle, user);
    }
}
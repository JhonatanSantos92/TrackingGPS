package com.gps.tracking.service;

import com.gps.tracking.clients.DeviceClient;
import com.gps.tracking.clients.UserClient;
import com.gps.tracking.clients.VehicleClient;
import com.gps.tracking.dto.DeviceDTO;
import com.gps.tracking.dto.TrackingResponseDTO;
import com.gps.tracking.dto.UserDTO;
import com.gps.tracking.dto.VehicleDTO;
import com.gps.tracking.entity.Assignment;
import com.gps.tracking.entity.Monitoring;
import com.gps.tracking.repository.AssignmentRepository;
import com.gps.tracking.repository.MonitoringRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class TrackingService {

    @Inject
    private AssignmentRepository assignmentRepository;
    @Inject
    private MonitoringRepository monitoringRepository;
    @RestClient
    @Inject
    private DeviceClient deviceClient;
    @RestClient
    @Inject
    private VehicleClient vehicleClient;
    @RestClient
    @Inject
    private UserClient userClient;

    public Uni<List<TrackingResponseDTO>> buildByAssignment(Long assignmentId) {

        Uni<Assignment> assignmentUni = assignmentRepository.findById(assignmentId);

        return assignmentUni.flatMap(assignment -> {
            if (assignment == null)
                return Uni.createFrom().item(List.of());

            return monitoringRepository.listByAssignment(assignmentId)
                    .flatMap(monitorings ->
                            Multi.createFrom().iterable(monitorings)
                                    .onItem().transformToUniAndMerge(
                                            m -> buildTracking(m, assignment)
                                    )
                                    .collect().asList()
                    );

        });
    }

    private Uni<TrackingResponseDTO> buildTracking(Monitoring monitoring, Assignment assignment) {

        Uni<DeviceDTO> device = deviceClient.getById(assignment.deviceId);
        Uni<VehicleDTO> vehicle = vehicleClient.getById(assignment.vehicleId);
        Uni<UserDTO> user =  userClient.getById(assignment.userId);

        return Uni.combine().all()
                .unis(device, vehicle, user)
                .asTuple()
                .map(t -> {
                    TrackingResponseDTO dto = new TrackingResponseDTO();
                    dto.latitud = monitoring.latitud;
                    dto.longitud = monitoring.longitud;
                    dto.velocidad = monitoring.velocidad;
                    dto.fecha = monitoring.fecha;
                    dto.device = t.getItem1();
                    dto.vehicle = t.getItem2();
                    dto.user = t.getItem3();
                    return dto;
                });
    }

}
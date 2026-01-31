package com.gps.tracking.resource;

import com.gps.tracking.dto.*;
import com.gps.tracking.entity.Assignment;
import com.gps.tracking.entity.Monitoring;
import com.gps.tracking.mapper.AssignmentMapper;
import com.gps.tracking.mapper.MonitoringMapper;
import com.gps.tracking.service.MonitoringService;
import com.gps.tracking.service.TrackingService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/tracking")
@Produces(MediaType.APPLICATION_JSON)
public class TrackingResource {

    private final MonitoringService monitoringService;
    private final TrackingService trackingService;

    public TrackingResource(MonitoringService monitoringService,
                            TrackingService trackingService) {
        this.monitoringService = monitoringService;
        this.trackingService = trackingService;
    }

    @GET
    public Response listAll() {
        List<Assignment> assignments = monitoringService.listAssignments();
        List<Monitoring> monitorings = monitoringService.listMonitoring();

        List<TrackingResponseDTO> response = monitorings.stream()
                .map(m -> buildTrackingSafe(m, assignments))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return Response.ok(response).build();
    }

    @GET
    @Path("/assignment/{id}")
    public Response getByAssignment(@PathParam("id") Long assignmentId) {

        Assignment assignment = monitoringService.listAssignments().stream()
                .filter(a -> a.id.equals(assignmentId))
                .findFirst()
                .orElse(null);

        if (assignment == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Assignment no existe")
                    .build();
        }

        List<TrackingResponseDTO> response = monitoringService.listMonitoring().stream()
                .filter(m -> m.assignmentId.equals(assignmentId))
                .map(m -> trackingService.buildTracking(
                        MonitoringMapper.toDTO(m),
                        AssignmentMapper.toDTO(assignment)
                ))
                .collect(Collectors.toList());

        return Response.ok(response).build();
    }

    private Optional<TrackingResponseDTO> buildTrackingSafe(
            Monitoring monitoring,
            List<Assignment> assignments
    ) {
        try {
            Assignment assignment = assignments.stream()
                    .filter(a -> a.id.equals(monitoring.assignmentId))
                    .findFirst()
                    .orElse(null);

            if (assignment == null) {
                return Optional.empty();
            }

            MonitoringDTO monitoringDTO = MonitoringMapper.toDTO(monitoring);
            AssignmentDTO assignmentDTO = AssignmentMapper.toDTO(assignment);

            return Optional.of(
                    trackingService.buildTracking(monitoringDTO, assignmentDTO)
            );

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @POST
    @Path("/assignment")
    public Response createAssignment(CreateAssignmentDTO dto) {
        Assignment a = monitoringService.createAssignment(dto);
        return Response.status(Response.Status.CREATED).entity(a).build();
    }

    @GET
    @Path("/assignment")
    public List<Assignment> listAssignments() {
        return monitoringService.listAssignments();
    }

    @POST
    @Path("/position")
    public Response createMonitoring(CreateMonitoringDTO dto) {
        Monitoring m = monitoringService.createMonitoring(dto);
        return Response.status(Response.Status.CREATED).entity(m).build();
    }

    @GET
    @Path("/position")
    public List<Monitoring> listMonitoring() {
        return monitoringService.listMonitoring();
    }
}

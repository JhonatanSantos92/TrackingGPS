package com.gps.tracking.resource;

import com.gps.tracking.dto.*;
import com.gps.tracking.entity.Assignment;
import com.gps.tracking.entity.Monitoring;
import com.gps.tracking.service.MonitoringService;
import com.gps.tracking.service.TrackingService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tracking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackingResource {

    @Inject
    private MonitoringService monitoringService;
    @Inject
    private TrackingService trackingService;

    @POST
    @Path("/assignment")
    public Uni<Response> createAssignment(CreateAssignmentDTO dto) {
        return monitoringService.createAssignment(dto)
                .map(a -> Response
                        .status(Response.Status.CREATED)
                        .entity(a)
                        .build());
    }

    @GET
    @Path("/assignment")
    public Uni<List<Assignment>> listAssignments() {
        return monitoringService.listAssignments();
    }

    @POST
    @Path("/position")
    public Uni<Response> createMonitoring(CreateMonitoringDTO dto) {
        return monitoringService.createMonitoring(dto)
                .map(m -> Response
                        .status(Response.Status.CREATED)
                        .entity(m)
                        .build());
    }

    @GET
    @Path("/position")
    public Uni<List<Monitoring>> listMonitoring() {
        return monitoringService.listMonitoring();
    }

    @GET
    @Path("/{assignmentId}")
    public Uni<List<TrackingResponseDTO>> getTracking(@PathParam("assignmentId") Long assignmentId) {
        return trackingService.buildByAssignment(assignmentId);
    }
}

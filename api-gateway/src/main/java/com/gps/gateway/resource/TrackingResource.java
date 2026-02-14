package com.gps.gateway.resource;

import com.gps.gateway.client.TrackingClient;
import com.gps.gateway.dto.*;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/tracking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackingResource {

    @Inject
    @RestClient
    TrackingClient trackingClient;

    @GET
    public Response listAll() {
        Uni<List<TrackingDTO>> response = trackingClient.getAll();
        return Response.ok(response).build();
    }

    @GET
    @Path("/assignment/{id}")
    public Uni<Response> getByAssignment(@PathParam("id") Long assignmentId) {
        Uni<TrackingDTO> response = trackingClient.getByAssignment(assignmentId);
        return response.map(r -> Response.ok(r).build());
    }

    @POST
    @Path("/assignment")
    public Uni<Response> createAssignment(CreateAssignmentDTO dto) {
        Uni<AssignmentDTO> created = trackingClient.createAssignment(dto);
        return created.map(a -> Response.status(Response.Status.CREATED).entity(a).build());
    }

    @GET
    @Path("/assignment")
    public Uni<Response> listAssignments() {
        Uni<List<AssignmentDTO>> assignments = trackingClient.listAssignments();
        return assignments.map(a -> Response.ok(a).build());
    }

    @POST
    @Path("/position")
    public Uni<Response> createMonitoring(CreateMonitoringDTO dto) {
        Uni<MonitoringDTO> created = trackingClient.createMonitoring(dto);
        return created.map(m -> Response.status(Response.Status.CREATED).entity(m).build());
    }

    @GET
    @Path("/position")
    public Uni<Response> listMonitorings() {
        Uni<List<MonitoringDTO>> monitorings = trackingClient.listMonitoring();
        return monitorings.map(m -> Response.ok(m).build());
    }
}


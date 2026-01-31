package com.gps.gateway.resource;

import com.gps.gateway.client.TrackingClient;
import com.gps.gateway.dto.*;
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
        List<TrackingDTO> response = trackingClient.getAll();
        return Response.ok(response).build();
    }

    @GET
    @Path("/assignment/{id}")
    public Response getByAssignment(@PathParam("id") Long assignmentId) {
        TrackingDTO[] response = trackingClient.getByAssignment(assignmentId);

        if (response == null || response.length == 0) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Assignment no existe")
                    .build();
        }

        return Response.ok(response).build();
    }

    @POST
    @Path("/assignment")
    public Response createAssignment(CreateAssignmentDTO dto) {
        AssignmentDTO created = trackingClient.createAssignment(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/assignment")
    public Response listAssignments() {
        List<AssignmentDTO> assignments = trackingClient.listAssignments();
        return Response.ok(assignments).build();
    }

    @POST
    @Path("/position")
    public Response createMonitoring(CreateMonitoringDTO dto) {
        MonitoringDTO created = trackingClient.createMonitoring(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/position")
    public Response listMonitorings() {
        List<MonitoringDTO> monitorings = trackingClient.listMonitoring();
        return Response.ok(monitorings).build();
    }
}


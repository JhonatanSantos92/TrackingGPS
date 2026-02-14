package com.gps.gateway.client;

import com.gps.gateway.dto.*;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/tracking")
@RegisterRestClient(configKey="tracking-api")
public interface TrackingClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<TrackingDTO>> getAll();

    @GET
    @Path("/assignment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<TrackingDTO> getByAssignment(@PathParam("id") Long assignmentId);

    @POST
    @Path("/assignment")
    Uni<AssignmentDTO> createAssignment(CreateAssignmentDTO dto);

    @GET
    @Path("/assignment")
    Uni<List<AssignmentDTO>> listAssignments();

    @POST
    @Path("/position")
    Uni<MonitoringDTO> createMonitoring(CreateMonitoringDTO dto);

    @GET
    @Path("/position")
    Uni<List<MonitoringDTO>> listMonitoring();

}

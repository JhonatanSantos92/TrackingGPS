package com.gps.gateway.client;

import com.gps.gateway.dto.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/tracking")
@RegisterRestClient(configKey="tracking-api")
public interface TrackingClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<TrackingDTO> getAll();

    @GET
    @Path("/assignment/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    TrackingDTO[] getByAssignment(@PathParam("id") Long assignmentId);

    @POST
    @Path("/assignment")
    AssignmentDTO createAssignment(CreateAssignmentDTO dto);

    @GET
    @Path("/assignment")
    List<AssignmentDTO> listAssignments();

    @POST
    @Path("/position")
    MonitoringDTO createMonitoring(CreateMonitoringDTO dto);

    @GET
    @Path("/position")
    List<MonitoringDTO> listMonitoring();

}

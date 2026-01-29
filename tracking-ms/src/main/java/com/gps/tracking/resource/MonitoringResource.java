package com.gps.tracking.resource;

import com.gps.tracking.dto.CreateAssignmentDTO;
import com.gps.tracking.dto.CreateMonitoringDTO;
import com.gps.tracking.entity.Assignment;
import com.gps.tracking.entity.Monitoring;
import com.gps.tracking.service.MonitoringService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/monitoring")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonitoringResource {

    @Inject
    MonitoringService service;

    @POST
    @Path("/assignment")
    public Response createAssignment(CreateAssignmentDTO dto) {
        Assignment a = service.createAssignment(dto);
        return Response.status(Response.Status.CREATED).entity(a).build();
    }

    @GET
    @Path("/assignment")
    public List<Assignment> listAssignments() {
        return service.listAssignments();
    }

    @POST
    @Path("/position")
    public Response createMonitoring(CreateMonitoringDTO dto) {
        Monitoring m = service.createMonitoring(dto);
        return Response.status(Response.Status.CREATED).entity(m).build();
    }

    @GET
    @Path("/position")
    public List<Monitoring> listMonitoring() {
        return service.listMonitoring();
    }
}
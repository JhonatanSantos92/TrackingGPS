package com.gps.alert.resource;

import com.gps.alert.dto.CreateAlertDTO;
import com.gps.alert.entity.Alert;
import com.gps.alert.service.AlertService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertResource {

    @Inject
    AlertService service;

    @POST
    public Response create(CreateAlertDTO dto) {
        Alert alert = service.processEvent(dto);
        if (alert == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.CREATED).entity(alert).build();
    }

    @GET
    public List<Alert> list() {
        return Alert.listAll();
    }

    @GET
    @Path("/{id}")
    public Alert get(@PathParam("id") Long id) {
        return Alert.findById(id);
    }
}
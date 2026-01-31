package com.gps.alert.resource;

import com.gps.alert.entity.Alert;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertResource {

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
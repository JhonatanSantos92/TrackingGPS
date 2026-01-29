package com.gps.gateway.resource;

import com.gps.gateway.client.AlertsClient;
import com.gps.gateway.dto.AlertDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertResource {

    @Inject
    @RestClient
    AlertsClient alertClient;

    @GET
    public List<AlertDTO> getAll() {
        return alertClient.getAll();
    }

    @GET
    @Path("/{id}")
    public AlertDTO getById(@PathParam("id") Long id) {
        return alertClient.getById(id);
    }

    @POST
    public Response create(AlertDTO dto) {
        alertClient.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    /*@PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, AlertDTO dto) {
        alertClient.update(id, dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        alertClient.delete(id);
        return Response.noContent().build();
    }*/
}

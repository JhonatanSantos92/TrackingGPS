package com.gps.gateway.resource;

import com.gps.gateway.client.TrackingClient;
import com.gps.gateway.dto.TrackingDTO;

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
    public List<TrackingDTO> getAll() {
        return trackingClient.getAll();
    }

    @GET
    @Path("/{id}")
    public TrackingDTO getById(@PathParam("id") Long id) {
        return trackingClient.getById(id);
    }

    @POST
    public Response create(TrackingDTO dto) {
        trackingClient.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TrackingDTO dto) {
        trackingClient.update(id, dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        trackingClient.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/last")
    public TrackingDTO getLast(@QueryParam("vehicleId") Long vehicleId) {
        return trackingClient.getLastByVehicle(vehicleId);
    }
}

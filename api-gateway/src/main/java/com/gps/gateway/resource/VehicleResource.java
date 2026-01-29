package com.gps.gateway.resource;

import com.gps.gateway.client.VehicleClient;
import com.gps.gateway.dto.VehicleDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleResource {

    @Inject
    @RestClient
    VehicleClient vehicleClient;

    @GET
    public List<VehicleDTO> getAll() {
        return vehicleClient.getAll();
    }

    @GET
    @Path("/{id}")
    public VehicleDTO getById(@PathParam("id") Long id) {
        return vehicleClient.getById(id);
    }

    @POST
    public Response create(VehicleDTO dto) {
        vehicleClient.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, VehicleDTO dto) {
        vehicleClient.update(id, dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        vehicleClient.delete(id);
        return Response.noContent().build();
    }
}

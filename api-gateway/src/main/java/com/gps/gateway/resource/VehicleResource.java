package com.gps.gateway.resource;

import com.gps.gateway.client.VehicleClient;
import com.gps.gateway.dto.VehicleDTO;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
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
    public Multi<VehicleDTO> getAll() {
        return vehicleClient.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<VehicleDTO> getById(@PathParam("id") Long id) {
        return vehicleClient.getById(id);
    }

    @POST
    public Uni<Response> create(VehicleDTO dto) {
        return vehicleClient.create(dto)
                .onFailure().recoverWithUni(f -> vehicleClient.create(dto));

    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, VehicleDTO dto) {
        return vehicleClient.update(id, dto)
                .onFailure().recoverWithUni(f -> vehicleClient.update(id, dto));
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return vehicleClient.delete(id)
                .onFailure().recoverWithUni(f -> vehicleClient.delete(id));
    }
}

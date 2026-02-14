package com.gps.gateway.resource;

import com.gps.gateway.client.DeviceClient;
import com.gps.gateway.dto.DeviceDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource {

    @Inject
    @RestClient
    DeviceClient deviceClient;

    @GET
    public Multi<DeviceDTO> getAll() {
        return deviceClient.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<DeviceDTO> getById(@PathParam("id") Long id) {
        return deviceClient.getById(id);
    }

    @POST
    public Uni<Response> create(DeviceDTO dto) {
        return deviceClient.create(dto)
                .map(created -> Response.status(Response.Status.CREATED).entity(created).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, DeviceDTO dto) {
        return deviceClient.update(id, dto)
                .map(updated -> Response.ok(updated).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return deviceClient.delete(id)
                .map(deleted -> Response.noContent().build());
    }
}

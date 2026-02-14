package com.gps.gateway.client;

import com.gps.gateway.dto.DeviceDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/devices")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey="device-api")
public interface DeviceClient {

    @GET
    Multi<DeviceDTO> getAll();

    @GET
    @Path("/{id}")

    Uni<DeviceDTO> getById(@PathParam("id") Long id);

    @POST
    Uni<Void> create(DeviceDTO dto);

    @PUT
    @Path("/{id}")
    Uni<Void> update(@PathParam("id") Long id, DeviceDTO dto);

    @DELETE
    @Path("/{id}")
    Uni<Void> delete(@PathParam("id") Long id);
}

package com.gps.gateway.client;

import com.gps.gateway.dto.DeviceDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/devices")
@RegisterRestClient(configKey="device-api")
public interface DeviceClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<DeviceDTO> getAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    DeviceDTO getById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void create(DeviceDTO dto);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(@PathParam("id") Long id, DeviceDTO dto);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}

package com.gps.gateway.resource;

import com.gps.gateway.client.DeviceClient;
import com.gps.gateway.dto.DeviceDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource {

    @Inject
    @RestClient
    DeviceClient deviceClient;

    @GET
    public List<DeviceDTO> getAll() {
        return deviceClient.getAll();
    }

    @GET
    @Path("/{id}")
    public DeviceDTO getById(@PathParam("id") Long id) {
        return deviceClient.getById(id);
    }

    @POST
    public Response create(DeviceDTO dto) {
        deviceClient.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, DeviceDTO dto) {
        deviceClient.update(id, dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        deviceClient.delete(id);
        return Response.noContent().build();
    }
}

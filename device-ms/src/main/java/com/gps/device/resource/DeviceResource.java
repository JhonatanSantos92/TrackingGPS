package com.gps.device.resource;

import com.gps.device.dto.CreateDeviceDTO;
import com.gps.device.dto.UpdateDeviceDTO;
import com.gps.device.entity.Device;
import com.gps.device.service.DeviceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource {

    @Inject
    DeviceService service;

    @POST
    public Response create(CreateDeviceDTO dto) {
        Device device = service.create(dto);
        return Response.status(Response.Status.CREATED).entity(device).build();
    }

    @GET
    public List<Device> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    public Device get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @PUT
    @Path("/{id}")
    public Device update(@PathParam("id") Long id, UpdateDeviceDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = service.delete(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
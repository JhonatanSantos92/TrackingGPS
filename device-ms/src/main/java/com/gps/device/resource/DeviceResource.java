package com.gps.device.resource;

import com.gps.device.dto.CreateDeviceDTO;
import com.gps.device.dto.UpdateDeviceDTO;
import com.gps.device.entity.Device;
import com.gps.device.service.DeviceService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource {

    @Inject
    DeviceService service;

    @POST
    public Uni<Response> create(CreateDeviceDTO dto) {
        return service.create(dto)
                .onItem()
                .transform(p ->
                        Response.status(Response.Status.CREATED).entity(p).build());
    }

    @GET
    public Multi<Device> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> get(@PathParam("id") Long id) {
        return service.get(id)
                .onItem().ifNotNull().transform(p -> Response.ok(p).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, UpdateDeviceDTO dto) {
        return service.update(id, dto)
                .onItem().transform(p -> Response.ok(p).build());
    }

}
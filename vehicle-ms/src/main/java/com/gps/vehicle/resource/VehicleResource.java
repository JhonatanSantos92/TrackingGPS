package com.gps.vehicle.resource;

import com.gps.vehicle.dto.CreateVehicleDTO;
import com.gps.vehicle.dto.UpdateVehicleDTO;
import com.gps.vehicle.entity.Vehicle;
import com.gps.vehicle.service.VehicleService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleResource {

    @Inject
    VehicleService service;

    @POST
    public Uni<Response> create(CreateVehicleDTO dto) {
        return service.create(dto)
                .onItem()
                .transform(p ->
                        Response.status(Response.Status.CREATED).entity(p).build());
    }

    @GET
    public Multi<Vehicle> list() {
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
    public Uni<Response> update(@PathParam("id") Long id, UpdateVehicleDTO dto) {
        return service.update(id, dto)
                .onItem().transform(p -> Response.ok(p).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return service.delete(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}
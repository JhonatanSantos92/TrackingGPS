package com.gps.vehicle.resource;

import com.gps.vehicle.dto.CreateVehicleDTO;
import com.gps.vehicle.dto.UpdateVehicleDTO;
import com.gps.vehicle.entity.Vehicle;
import com.gps.vehicle.service.VehicleService;
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
    public Response create(CreateVehicleDTO dto) {
        Vehicle vehicle = service.create(dto);
        return Response.status(Response.Status.CREATED).entity(vehicle).build();
    }

    @GET
    public List<Vehicle> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    public Vehicle get(@PathParam("id") Long id) {
        return service.get(id);
    }

    @PUT
    @Path("/{id}")
    public Vehicle update(@PathParam("id") Long id, UpdateVehicleDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = service.delete(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
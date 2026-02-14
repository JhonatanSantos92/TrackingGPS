package com.gps.gateway.client;

import com.gps.gateway.dto.VehicleDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey="vehicle-api")
public interface VehicleClient {

    @GET
    Multi<VehicleDTO> getAll();

    @GET
    @Path("/{id}")

    Uni<VehicleDTO> getById(@PathParam("id") Long id);

    @POST
    Uni<Response> create(VehicleDTO dto);

    @PUT
    @Path("/{id}")
    Uni<Response> update(@PathParam("id") Long id, VehicleDTO dto);

    @DELETE
    @Path("/{id}")
    Uni<Response> delete(@PathParam("id") Long id);
}

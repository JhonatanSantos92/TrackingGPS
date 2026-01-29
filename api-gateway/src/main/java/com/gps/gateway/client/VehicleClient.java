package com.gps.gateway.client;

import com.gps.gateway.dto.VehicleDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/vehicles")
@RegisterRestClient(configKey="vehicle-api")
public interface VehicleClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<VehicleDTO> getAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    VehicleDTO getById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void create(VehicleDTO dto);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(@PathParam("id") Long id, VehicleDTO dto);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}

package com.gps.gateway.client;

import com.gps.gateway.dto.TrackingDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/tracking")
@RegisterRestClient(configKey="tracking-api")
public interface TrackingClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<TrackingDTO> getAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    TrackingDTO getById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void create(TrackingDTO dto);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(@PathParam("id") Long id, TrackingDTO dto);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);

    @GET
    @Path("/last")
    TrackingDTO getLastByVehicle(@QueryParam("vehicleId") Long vehicleId);
}

package com.gps.tracking.clients;

import com.gps.tracking.dto.VehicleDTO;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/vehicles")
@RegisterRestClient(configKey = "vehicle-ms")
@Produces(MediaType.APPLICATION_JSON)
public interface VehicleClient {

    @GET
    @Path("/{id}")
    Uni<VehicleDTO> getById(@PathParam("id") Long id);
}
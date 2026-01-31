package com.gps.tracking.clients;

import com.gps.tracking.dto.VehicleDTO;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
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
    @Retry(maxRetries = 1, delay = 200)
    VehicleDTO getById(@PathParam("id") Long id);
}
package com.gps.tracking.clients;

import com.gps.tracking.dto.DeviceDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/devices")
@RegisterRestClient(configKey="device-ms")
public interface DeviceClient {

    @GET
    @Path("/{id}")
    @Timeout(1000)
    @Retry(maxRetries = 1, delay = 200)
    @Produces(MediaType.APPLICATION_JSON)
    DeviceDTO getById(@PathParam("id") Long id);

}

package com.gps.gateway.client;

import com.gps.gateway.dto.AlertDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/alerts")
@RegisterRestClient(configKey="alert-api")
public interface AlertsClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<AlertDTO> getAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    AlertDTO getById(@PathParam("id") Long id);

}

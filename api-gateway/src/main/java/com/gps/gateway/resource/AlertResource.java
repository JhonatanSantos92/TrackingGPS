package com.gps.gateway.resource;

import com.gps.gateway.client.AlertsClient;
import com.gps.gateway.dto.AlertDTO;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertResource {

    @Inject
    @RestClient
    AlertsClient alertClient;

    @GET
    public Uni<List<AlertDTO>> getAll() {
        return alertClient.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<AlertDTO> getById(@PathParam("id") Long id) {
        return alertClient.getById(id);
    }


}

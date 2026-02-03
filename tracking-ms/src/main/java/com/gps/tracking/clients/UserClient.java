package com.gps.tracking.clients;

import com.gps.tracking.dto.UserDTO;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.core.MediaType;

@Path("/api/users")
@RegisterRestClient(configKey = "user-ms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserClient {

    @GET
    @Path("/{id}")
    Uni<UserDTO> getById(@PathParam("id") Long id);
}
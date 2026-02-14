package com.gps.gateway.client;

import com.gps.gateway.dto.UserDTO;
import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import io.smallrye.mutiny.Uni;

@Path("/api/users")
@RegisterRestClient(configKey="user-api")
public interface UserClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Multi<UserDTO> getAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Response> getById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> create(UserDTO user);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> update(@PathParam("id") Long id, UserDTO user);

    @DELETE
    @Path("/{id}")
    Uni<Response> delete(@PathParam("id") Long id);
}

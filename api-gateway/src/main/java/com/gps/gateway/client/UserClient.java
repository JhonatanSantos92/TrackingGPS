package com.gps.gateway.client;

import com.gps.gateway.dto.UserDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/users")
@RegisterRestClient(configKey="user-api")
public interface UserClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<UserDTO> getAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    UserDTO getById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void create(UserDTO user);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(@PathParam("id") Long id, UserDTO user);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}

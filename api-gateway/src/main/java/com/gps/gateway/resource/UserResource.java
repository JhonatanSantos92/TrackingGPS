package com.gps.gateway.resource;

import com.gps.gateway.client.UserClient;
import com.gps.gateway.dto.UserDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    @RestClient
    UserClient userClient;

    @GET
    public List<UserDTO> getAll() {
        return userClient.getAll();
    }

    @GET
    @Path("/{id}")
    public UserDTO getById(@PathParam("id") Long id) {
        return userClient.getById(id);
    }

    @POST
    public Response create(UserDTO dto) {
        userClient.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserDTO dto) {
        userClient.update(id, dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        userClient.delete(id);
        return Response.noContent().build();
    }
}

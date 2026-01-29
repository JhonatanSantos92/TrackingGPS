package com.gps.user.resource;

import com.gps.user.dto.CreateUserDTO;
import com.gps.user.dto.UpdateUserDTO;
import com.gps.user.entity.User;
import com.gps.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @POST
    public Response create(CreateUserDTO dto) {
        User user = userService.create(dto);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    public Response getAll() {
        List<User> users = userService.findAll();
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        User user = userService.findById(id);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UpdateUserDTO dto) {
        User user = userService.update(id, dto);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        userService.delete(id);
        return Response.noContent().build();
    }
}
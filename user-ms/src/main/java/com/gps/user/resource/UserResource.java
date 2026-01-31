package com.gps.user.resource;

import com.gps.user.entity.User;
import com.gps.user.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response create(User user) {
        User created = userService.create(
                user.nombre,
                user.correo
        );
        return Response
                .status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @GET
    public List<User> findAll() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, User user) {
        User updated = userService.update(
                id,
                user.nombre,
                user.correo,
                user.status
        );

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = userService.delete(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }
}

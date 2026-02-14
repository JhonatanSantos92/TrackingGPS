package com.gps.user.resource;

import com.gps.user.dto.UserDTO;
import com.gps.user.entity.User;
import com.gps.user.service.UserService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
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

    @Inject
    UserService service;

    @POST
    public Uni<Response> create(UserDTO user) {
        return service.create(user)
                .onItem()
                .transform(p ->
                        Response.status(Response.Status.CREATED).entity(p).build());
    }

    @GET
    public Multi<User> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> get(@PathParam("id") Long id) {
        return service.get(id)
                .onItem().ifNotNull().transform(p -> Response.ok(p).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, UserDTO user) {
        return service.update(id, user)
                .onItem().transform(p -> Response.ok(p).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return service.delete(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}

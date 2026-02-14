package com.gps.gateway.resource;

import com.gps.gateway.client.BackupClient;
import com.gps.gateway.client.UserClient;
import com.gps.gateway.dto.UserDTO;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Uni;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    @RestClient
    UserClient userClient;

    @Inject
    @RestClient
    BackupClient backupClient;

    @GET
    public Multi<UserDTO> getAll() {
        return userClient.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getById(@PathParam("id") Long id) {
        return userClient.getById(id)
                .onFailure().recoverWithUni(f -> userClient.getById(id));
    }

    @POST
    public Uni<Response> create(UserDTO dto) {
        return userClient.create(dto)
                .flatMap(resp -> is2xx(resp) ? Uni.createFrom()
                        .item(Response.status(Response.Status.CREATED)
                            .entity("User created successfully")
                            .build()) : tryBackup(dto))
                .onFailure()
                .recoverWithUni(f -> tryBackup(dto));
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> update(@PathParam("id") Long id, UserDTO dto) {
        return userClient.update(id, dto)
                .onFailure().recoverWithUni(f -> userClient.update(id, dto));
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return userClient.delete(id)
                .onFailure().recoverWithUni(f -> userClient.delete(id));
    }

    private Uni<Response> tryBackup(UserDTO dto) {
        return backupClient.backup(dto)
                .flatMap(backupResp -> is2xx(backupResp) ? Uni.createFrom().item(
                    Response.status(Response.Status.CREATED)
                            .entity("Backup successful, user created in backup system")
                            .build()
                ) : Uni.createFrom().item(
                    Response.status(Response.Status.BAD_GATEWAY)
                            .entity("Backup failed with status: " + backupResp.getStatus())
                            .build()
                        )
                .onFailure().recoverWithItem(ex ->
                    Response.status(Response.Status.BAD_GATEWAY)
                            .entity("Backup failed: " + ex.getMessage())
                            .build()
                ));
    }

    private boolean is2xx(Response resp) {
        return resp != null && resp.getStatus() >= 200 && resp.getStatus() < 300;
    }
}

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
                .flatMap(resp -> {
                    int status = resp.getStatus();
                    if (status >= 200 && status < 300) {
                        return Uni.createFrom().item(Response.status(Response.Status.CREATED).build());
                    }
                    return backupClient.backup(dto)
                            .flatMap(backupResp -> {
                                int bStatus = backupResp.getStatus();
                                if (bStatus >= 200 && bStatus < 300) {
                                    return Uni.createFrom()
                                            .item(Response.status(Response.Status.CREATED).build());
                                }
                                return Uni.createFrom()
                                        .item(Response.status(Response.Status.BAD_GATEWAY)
                                                .entity("Backup failed").build());
                            });
                })
                .onFailure().recoverWithUni(f -> backupClient.backup(dto)
                        .flatMap(backupResp -> {
                            int bStatus = backupResp.getStatus();
                            if (bStatus >= 200 && bStatus < 300) {
                                return Uni.createFrom().
                                        item(Response.status(Response.Status.CREATED)
                                                .build());
                            }
                            return Uni.createFrom()
                                    .item(Response
                                            .status(Response.Status.BAD_GATEWAY)
                                            .entity("Backup failed").build());
                        })
                        .onFailure()
                        .recoverWithItem(backupEx -> Response
                                .status(Response.Status.BAD_GATEWAY)
                                .entity("Upstream failure")
                                .build()));
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
}

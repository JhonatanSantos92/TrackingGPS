package com.gps.gateway.client;

import com.gps.gateway.dto.UserDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import io.smallrye.mutiny.Uni;

@Path("/api/backup")
@RegisterRestClient(configKey="backup-api")
public interface BackupClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> backup(UserDTO dto);
}

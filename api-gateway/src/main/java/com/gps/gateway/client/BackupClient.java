package com.gps.gateway.client;

import com.gps.gateway.dto.UserDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/backup")
@RegisterRestClient(configKey="backup-api")
public interface BackupClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void backup(UserDTO dto);
}

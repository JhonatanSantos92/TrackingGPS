package com.gps.gateway.client;

import com.gps.gateway.dto.UserBackupDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@Path("/api/backup")
@RegisterRestClient(configKey="backup-api")
public interface BackupClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<UserBackupDTO> getAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void backup(UserBackupDTO dto);
}

package com.gps.userbackup.resource;

import com.gps.userbackup.dto.UserBackupDTO;
import com.gps.userbackup.entity.UserBackup;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/backup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BackupResource {

    @POST
    @Transactional
    public Response backup(UserBackupDTO dto) {

        UserBackup entity = new UserBackup();
        entity.nombre = dto.nombre;
        entity.correo = dto.correo;
        entity.status = dto.status;

        entity.persist();

        return Response.status(Response.Status.CREATED).build();
    }

}


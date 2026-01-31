package com.gps.userbackup.exception;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PersistenceExceptionMapper
        implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException e) {

        Throwable cause = e.getCause();

        if (cause instanceof org.hibernate.exception.ConstraintViolationException cve) {

            String message = resolveMessage(cve.getConstraintName());

            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorDTO("409", message))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO("500", "Error de base de datos"))
                .build();
    }

    private String resolveMessage(String constraint) {

        if (constraint == null) {
            return "Violación de integridad de datos";
        }

        if (constraint.contains("users.nombre")) {
            return "El nombre ya existe";
        }

        if (constraint.contains("users.correo")) {
            return "El correo ya está registrado";
        }

        return "Datos duplicados o inválidos";
    }
}

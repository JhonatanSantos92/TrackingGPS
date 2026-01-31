package com.gps.gateway.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ProcessingException;

@Provider
public class ProcessingExceptionMapper
        implements ExceptionMapper<ProcessingException> {

    @Override
    public Response toResponse(ProcessingException e) {

        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(new ErrorResponse(
                        "El servicio no está disponible en este momento"
                ))
                .build();
    }
}
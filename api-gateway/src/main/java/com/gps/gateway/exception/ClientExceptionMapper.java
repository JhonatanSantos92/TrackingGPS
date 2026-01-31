package com.gps.gateway.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

@Provider
public class ClientExceptionMapper
        implements ExceptionMapper<ClientWebApplicationException> {

    @Override
    public Response toResponse(ClientWebApplicationException e) {

        Response r = e.getResponse();
        String body = r.readEntity(String.class);

        return Response.status(r.getStatus())
                .entity(body)
                .build();
    }
}
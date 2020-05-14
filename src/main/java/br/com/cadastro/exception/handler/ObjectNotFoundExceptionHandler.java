package br.com.cadastro.exception.handler;

import br.com.cadastro.exception.custom.ObjectNotFoundException;
import com.google.gson.Gson;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ObjectNotFoundExceptionHandler implements ExceptionMapper<ObjectNotFoundException> {
    @Override
    public Response toResponse(ObjectNotFoundException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new Gson().toJson(e.getMessage()))
                .build();
    }
}

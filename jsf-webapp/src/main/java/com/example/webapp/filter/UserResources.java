package com.example.webapp.filter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/users")
@Secure
public class UserResources {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUsers() {
        return Response.status(Status.OK).entity("getUsers").build(); 
    }
}

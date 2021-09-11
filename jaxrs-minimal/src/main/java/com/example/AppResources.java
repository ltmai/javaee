package com.example;
 
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

 
@Path("/api")
public class AppResources
{
    @GET
	@PermitAll
    @Path("/users/{id}")
    public Response getUserById(@PathParam("id") int id, @Context Request req)
    {
        Response.ResponseBuilder rb = Response.ok("{ id : 1 }");
        return rb.build();
    }
     
    @PUT
	@RolesAllowed("ADMIN")
    @Path("/users/{id}")
    public Response updateUserById(@PathParam("id") int id)
    {
        return Response.status(200).build();
    }
}
package mai.linh.webservice;

import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * WebServices
 */
@Stateless
@Path("/dummy")
public class WebServices {

    @GET
    @Path("/time")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTime() {
        return Response.ok(LocalDateTime.now().toString()).build();
    }
}
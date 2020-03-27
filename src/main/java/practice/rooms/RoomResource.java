package practice.rooms;

import practice.rooms.Services.RoomService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("")
public class RoomResource {

    @GET
    @Path("/check")
    @Produces("application/json")
    public Response Check(@QueryParam("roomId") int roomId, @QueryParam("entrance") boolean entrance, @QueryParam("keyId")int userId) {
        var result = RoomService.Check(roomId, entrance, userId);
        return Response.status(result.getCode()).entity(result).build();
    }
}

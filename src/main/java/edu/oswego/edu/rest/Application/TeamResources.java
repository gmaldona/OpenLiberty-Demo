package edu.oswego.edu.rest.Application;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.oswego.edu.rest.datastore.TeamNameDatastore;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


@Path("/teamnames")

public class TeamResources {
    private static final Jsonb jsonb = JsonbBuilder.create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamNames() {
        String responseMessage = jsonb.toJson(TeamNameDatastore.teamName.toArray());
        return Response.status(Response.Status.CREATED).entity(responseMessage).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeamName(@RequestBody JsonObject teamNameBody) {
        String nameA = teamNameBody.getString("nameA");
        String nameB = teamNameBody.getString("nameB");
        String yearA = teamNameBody.getString("yearA");
        String yearB = teamNameBody.getString("yearB");

        String response = nameA + yearA + nameB + yearB;
        TeamNameDatastore.teamName.add(response);

        //return Response.status(Response.Status.CREATED).entity(TeamNameDatastore.teamName.size()).build();
        return Response.status(Response.Status.OK).build();
    }

}
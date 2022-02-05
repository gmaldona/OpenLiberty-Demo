package edu.oswego.edu.rest;

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

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.ArrayList;

@Path("/teamname")

public class TeamResources {
    ArrayList<String> teamName = new ArrayList<>();
    private static final Jsonb jsonb = JsonbBuilder.create();

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamNames(){
        StringBuilder sb = new StringBuilder();
        teamName.forEach(name -> sb.append(teamName));
        String responseMessage = sb.toString();
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
        teamName.add(response);

        return Response.status(Response.Status.OK).build();
    }

}
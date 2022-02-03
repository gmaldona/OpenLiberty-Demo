package edu.oswego.edu.rest;

import javax.json.Json;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/TeamName")

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



}
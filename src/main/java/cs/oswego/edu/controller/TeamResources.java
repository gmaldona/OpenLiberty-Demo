package edu.oswego.edu.rest.Application.resources;

import java.io.StringWriter;

import javax.inject.Inject;
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

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.oswego.edu.rest.datastore.TeamNameDatastore;

import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


@Path("/teamnames")

public class TeamResources {
    private static final Jsonb jsonb = JsonbBuilder.create();
    
    // Mongo init
    @Inject MongoDatabase db;
    
    // @route:   Post /teamnames/addTeamName
    // @desc:    Creates new teamName and write it to the DB
    // // @access   public
    @POST
    @Path("/addteamname")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTeamNameToDB(@RequestBody JsonObject teamNameBody) {

        String nameA = teamNameBody.getString("nameA");
        String nameB = teamNameBody.getString("nameB");
        String yearA = teamNameBody.getString("yearA");
        String yearB = teamNameBody.getString("yearB");

        String teamName = nameA + yearA + nameB + yearB;

        MongoCollection<Document> teamNames = db.getCollection("TeamNames"); // MongoCollection instance, like a table in sql
        Document newTeamName = new Document();
		newTeamName.put("Team Name",teamName);

		teamNames.insertOne(newTeamName); // populate the record to the instance

        return Response.status(Response.Status.OK).build();
    }


    // @route:   GET /teamnames/addTeamName
    // @desc:    Get team names
    // @access   public
    @GET
    @Path("/addteamname")
    public String retrieve() {
        StringWriter sb = new StringWriter();
        try {
        //     // Document is like a placeholder, represent a map
			MongoCollection<Document> teamNames = db.getCollection("TeamNames"); // get collection instance
        
            sb.append("[");

			boolean first = true;
			for (Document d : teamNames.find()) { // Document d = JSONObject
				if (!first) sb.append(",");
				else first = false;
				sb.append(" " + d.getString("Team Name"));
			}
			sb.append("]");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return sb.toString();
    }




    // @route:   GET /teamnames
    // @desc:    Get team names
    // @access   public
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamNames() {
        String responseMessage = jsonb.toJson(TeamNameDatastore.teamName.toArray());
        return Response.status(Response.Status.CREATED).entity(responseMessage).build();
    }

    // @route:   Post teamnames
    // @desc:    Creates new teamName
    // @access   public
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
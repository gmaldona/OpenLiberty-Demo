package cs.oswego.edu.controller;

import java.io.StringWriter;
import java.util.List;

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

import cs.oswego.edu.model.TeamName;
import org.bson.Document;


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
    public Response addTeamNameToDB(TeamName teamName) {

        MongoCollection<Document> teamNames = db.getCollection("TeamNames"); // MongoCollection instance, like a table in sql
        Document newTeamName = new Document();
		newTeamName.put("Team Name", teamName.generateTeamName());

		teamNames.insertOne(newTeamName); // populate the record to the instance

        return Response.status(Response.Status.OK).build();
    }


    // @route:   GET /teamnames/addTeamName
    // @desc:    Get team names
    // @access   public
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
}
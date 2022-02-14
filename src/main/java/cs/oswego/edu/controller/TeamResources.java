package cs.oswego.edu.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import cs.oswego.edu.model.TeamName;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringWriter;


@Path("/teamnames")

public class TeamResources {
    private static final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    MongoDatabase database;

    @Inject
    @ConfigProperty(name = "mongo.collection")
    String collection;

    // @route:   Post /teamnames/addTeamName
    // @desc:    Creates new teamName and write it to the DB
    // @access   public
    @POST
    @Path("/addteamname")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTeamNameToDB(TeamName teamName) {

        MongoCollection<Document> teamNames = database.getCollection(collection);
        Document newTeamName = new Document();
        newTeamName.put("Team Name", teamName.generateTeamName());

        teamNames.insertOne(newTeamName);

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
            MongoCollection<Document> teamNames = database.getCollection(collection);

            sb.append("[");

            boolean first = true;
            for (Document d : teamNames.find()) {
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
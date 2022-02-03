package edu.oswego.edu.rest;

import javax.json.Json;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Path;

@Path("/TeamName")

public class TeamResources {
    private static final Jsonb jsonb = JsonbBuilder.create();

}
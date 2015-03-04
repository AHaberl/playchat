package controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import models.Server;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerController extends Controller{
	
	public static List<Server> servers = new LinkedList<Server>();
	public static ObjectMapper mapper = new ObjectMapper();

	
	public static Result newServer() throws JsonParseException, JsonMappingException, IOException{
		
		JsonNode json = request().body().asJson();
		
		Server server = mapper.readValue(json.toString(), Server.class);
		servers.add(server);
		
		return ok("server added");
	}
	
	
	public static Result getServer(){
		
		JsonNode json = Json.toJson(servers);
		
		return ok(json);
	}
	
	
}

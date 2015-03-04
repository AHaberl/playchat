package controllers;

import java.io.IOException;

import models.Message;
import models.Messages;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.messageview;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class UserController extends Controller{	
	
	private static Messages messages = new Messages();
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static Result getMessages(){
		
		JsonNode json = Json.toJson(messages.getMessages());
		
		return ok(json);
	}

	
	public static Result newMessage() throws JsonParseException, JsonMappingException, IOException{		
		JsonNode json = request().body().asJson();
				
				
		Message message = mapper.readValue(json.toString(), Message.class);
		messages.addMessage(message);
		
		return ok();
	}
	
		
}

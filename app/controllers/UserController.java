package controllers;

import helper.HttpHelper;
import helper.ServerHelper;

import java.io.IOException;

import models.Message;
import models.Server;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController extends Controller{	

	private static ObjectMapper mapper = new ObjectMapper();
	
	public static Result getMessages(){
		
		//EMF hier so
		//JsonNode json = Json.toJson(messages.getMessages());
		
		return ok();
	}

	
	public static Result newMessage() throws JsonParseException, JsonMappingException, IOException{		
		JsonNode json = request().body().asJson();
		
		String serverName = "Server1";
		
		System.out.println("new message" + json.toString());
				
		Message message = mapper.readValue(json.toString(), Message.class);
		
		
		if(null == message.getUID()){
			message.setUID(ServerHelper.generateUID(serverName));
			message.setOrigin(serverName);
			//persist message on local db
			
			for(Server server : ServerController.servers){
				HttpHelper.sendMessage(server, message);
			}	
		} else {
			//check if uid is present on server -> direct early return, exit from else
			//if yes do nothing - if no persist message
			
			for(Server server : ServerController.servers){
				if(!server.getName().equals(message.getOrigin())){
					HttpHelper.sendMessage(server, message);
				}
			}
		}
		
		return ok();
	}
	
	public static Result register(){
		return ok();
	}
		
	public static Result login(){
		return ok();
	}
	
	
}

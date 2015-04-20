package controllers;

import helper.HttpHelper;
import helper.ServerHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import models.Configuration;
import models.Message;
import models.Server;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController extends Controller{	

	private static ObjectMapper mapper = new ObjectMapper();
	
	public static Result getMessages(){
		String status = session("status");
		
		if(!"online".equals(status)){
			return redirect(routes.UserController.login());
		}
		
		//EMF hier so
		//JsonNode json = Json.toJson(messages.getMessages());
		
		return ok();
	}

	//remove when persistence is implemented
	@SuppressWarnings("unused")
	public static Result newMessage() throws JsonParseException, JsonMappingException, IOException{		
		JsonNode json = request().body().asJson();
		
		System.out.println("new message" + json.toString());
				
		Message message = mapper.readValue(json.toString(), Message.class);
		
		if(null == message.getData().getSender()){
			return badRequest("sender shall not be empty");
		}
		
		User user = null;
//		User user = user from reddis: message.getData().getSender();
//		if(null == user){
//			return badRequest("sender must be valid user");
//		}
		
		
		if(null == message.getUID()){
			message.setUID(ServerHelper.generateUID());
			message.setOrigin(Configuration.getServerName());
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
		
		return ok("{}");
	}
	
	public static Result register(){
		return ok();
	}
		
	public static Result login() throws JsonParseException, JsonMappingException, IOException{
		
		JsonNode json = request().body().asJson();
		User user = mapper.readValue(json.toString(), User.class);
		
		if(null == user.getPassword()){
			return unauthorized("empty password");
		}
		
		if(null == user.getUserName()){
			return unauthorized("empty username");
		}
		
		if(user.login()){
			session("status", "online");
			session("username", user.getUserName());

	    	Long nextID = ServerController.cluster.incr("userid");
	    	user.setID(nextID.toString());
	    	
	    	ServerController.cluster.hset("users", user.getUserName(), user.getID());
	    	ServerController.cluster.hmset("user:"+user.getID(), UserController.convertUser(user));
		}
		
		String welcome = "{\"message\" : \"welcome " + user.getUserName() + "\"}";
		return ok(welcome);
	}
	
	
	public static Map<String,String> convertUser(User user){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", user.getUserName());
		map.put("password", user.getPassword());
		map.put("status", user.getStatus());
		return map;
	}
	
	public static Result logout(){
		
		 session("status", "");
		 session("username", "");

		 return redirect(routes.ViewController.login());
		
		 }	
}

package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Message;
import models.MessageData;
import models.User;
import play.Logger;
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
		String status = session("status");
		
		if(!"online".equals(status)){
			return redirect(routes.UserController.login());
		}
		List<Message> messages = UserController.getAllMessages();
		
		for(Message message: messages){
			System.out.println(message.getData().toString());
		}
		
		//TODO converting to JSON
		JsonNode json = Json.toJson(messages);

		return ok(json.toString());
	}

	//remove when persistence is implemented
	@SuppressWarnings("unused")
	public static Result newMessage() throws JsonParseException, JsonMappingException, IOException{		
		JsonNode json = request().body().asJson();
		
		System.out.println("new message" + json.toString());
				
		Message message = mapper.readValue(json.toString(), Message.class);
		
		User user = new User();
		user.setUserName(message.getData().getSender());
		
		String id = ServerController.cluster.hget("users", user.getUserName());
		if(id == null)
			return badRequest("sender must be valid user");
		
		String status = session("status");
		if("".equals(status))
			return badRequest("you must be logged in to chat");
		
		if(null == message.getData().getSender())
			return badRequest("sender shall not be empty");
		
		if(message.getData().getMessage().equals(null) || message.getData().getMessage().equals(""))
			return badRequest("content must not be empty");
		
		Long nextMessageID = ServerController.cluster.incr("messageID");
		message.setUID(String.valueOf(nextMessageID));
		ServerController.cluster.hmset("message:" + message.getUID(), UserController.convertMessage(message));
		ServerController.cluster.lpush("messages", message.getUID());
		
		return ok("{}");
	}
	
	public static Result register(){
		
		String u = "";
		String password = "";
		String userName = "";
		
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
		
		return ok("welcome " + user.getUserName());
	}
	
	public static Map<String,String> convertUser(User user){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", user.getUserName());
		map.put("password", user.getPassword());
		map.put("status", user.getStatus());
		return map;
	}
	
	public static Map<String,String> convertMessage(Message message){
		Map<String, String> map = new HashMap<String, String>();
		map.put("sender", message.getData().getSender());
		map.put("content", message.getData().getMessage());
		return map;
	}
	
	public static List<Message> getAllMessages(){
		List<Message> messageList = new ArrayList<Message>();
		List<String> messageRange = ServerController.cluster.lrange("messages", 0, -1);
		for(String id: messageRange){
			List<String> currentQuery = ServerController.cluster.hmget("message:" + id, "sender", "content");
			Message currentMessage = new Message();
			currentMessage.setUID(id);
			currentMessage.setData(new MessageData(currentQuery.get(0), currentQuery.get(1)));
			messageList.add(currentMessage);
		}

		return messageList;
	}
	
	public static Result logout(){
		
		 session().clear();
		 return redirect(routes.ViewController.login());
		
		 }	
}

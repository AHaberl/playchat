package helper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import play.libs.Json;
import models.Message;
import models.Server;

import com.fasterxml.jackson.databind.JsonNode;

public class HttpHelper {

	
	
	public static void sendMessage(Server server,  Message message) throws IOException{
		
		String targetUrl = server.getName() + ":" + server.getPort();
		URL url = new URL(targetUrl);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		JsonNode json = Json.toJson(message);
		
		out.writeBytes(json.toString());
		out.flush();
		out.close();
		
		
	}
	
	
	
}

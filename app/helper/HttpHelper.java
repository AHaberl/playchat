package helper;

import java.io.IOException;

import models.Message;
import models.Server;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

public class HttpHelper {

	
	
	public static void sendMessage(Server server,  Message message) throws IOException{
		
		String targetUrl = "http://" + server.getUrl() + ":" + server.getPort() + "/messages/new";
		JsonNode json = Json.toJson(message);
	
		HttpClient http = new DefaultHttpClient();
		HttpPost request = new HttpPost(targetUrl);
		
		StringEntity params = new StringEntity(json.toString());
		request.addHeader("content-type", "application/json");
		request.setEntity(params);
		
		HttpResponse response = http.execute(request);
		
		http.getConnectionManager().shutdown();
		
		
	}
	
	
	
}

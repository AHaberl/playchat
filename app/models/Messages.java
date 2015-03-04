package models;

import java.util.LinkedList;
import java.util.List;

public class Messages {

	private List<Message> messages;
	
	public Messages(){
		this.messages = new LinkedList<Message>();
	}
	
	public List<Message> getMessages(){
		return this.messages;
	}
	
	
	public void addMessage(Message message){
		this.messages.add(message);
	}
	
	public String toString(){
		String result = "";
		for(Message message : messages){
			result += message.toString();
		}
		return result;
	}
}

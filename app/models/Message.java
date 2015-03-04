package models;

public class Message {

	private String type;
	private MessageData data;
	
	public Message(MessageData data){
		this.data = data;
	}
	
	public Message(){
		
	}
	
	
	public String toString(){
		return this.data.toString();
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public MessageData getData() {
		return data;
	}


	public void setData(MessageData data) {
		this.data = data;
	}
	
	
	
	
}

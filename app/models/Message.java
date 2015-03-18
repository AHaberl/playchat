package models;

public class Message {

	private String type;
	private MessageData data;
	private String UID;
	
	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}

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

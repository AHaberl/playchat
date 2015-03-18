package models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	private String type;
	private MessageData data;
	private String UID;
	private String origin;
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

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

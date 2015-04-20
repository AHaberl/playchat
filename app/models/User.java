package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class User {
	private long id;
	
	private String userName;
	private String status;
	
	private String password;
	
	private List<Message> messages = new ArrayList<Message>();
	
	private Date createdAt;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	
	public boolean login(){
		//TODO: check username, password
		
		status = "online";
		
		return true;
	}
}
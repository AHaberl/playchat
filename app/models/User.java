package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import controllers.ServerController;
import controllers.UserController;


public class User {
	private String id;
	
	private String userName;
	private String status;
	
	private String password;
	
	private List<Message> messages = new ArrayList<Message>();
	
	private Date createdAt;
	
	public User(){
		super();
	}
	
	public User(String name, String pass){
		this.userName = name;
		this.password = pass;
		this.setID(ServerController.cluster.incr("userid").toString());
		this.setStatus("");
	}

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
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getID(){
		return this.id;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public boolean login(){
		//TODO: check username, password
		String userid = ServerController.cluster.hget("users", this.userName);
		if (userid == null)
			return false;
		String pass = ServerController.cluster.hget("user:" + userid, "password");
		if (pass == null || !pass.equals(pass))
			return false;

		this.status = "online";
		return true;
	}
}
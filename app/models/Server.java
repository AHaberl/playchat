package models;

public class Server {

	private String url;
	private int port;	
	private String name;
	
	
	public Server(String name, String url, int port){
		this.name = name;
		this.url = url;
		this.port = port;
	}
	
	public Server(){
		
	}	


	public String getUrl() {
		return url;
	}


	public int getPort() {
		return port;
	}


	public String getName() {
		return name;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}

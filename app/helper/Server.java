package helper;

public class Server {

	private String url;
	private int port;	
	private String name;
	
	
	public Server(String name, String url, int port){
		this.name = name;
		this.url = url;
		this.port = port;
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
	
	
	
	
	
}

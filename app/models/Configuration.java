package models;

public class Configuration {
	
	private static String serverName = "server1";
	private static int serverPort;
	
	
	public static String getServerName() {
		return serverName;
	}
	public static void setServerName(String serverName) {
		Configuration.serverName = serverName;
	}
	public static int getServerPort() {
		return serverPort;
	}
	public static void setServerPort(int serverPort) {
		Configuration.serverPort = serverPort;
	}

}

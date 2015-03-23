package helper;

import models.Configuration;

public class ServerHelper {

	
	public static String generateUID(){
		return Configuration.getServerName();
	}
}

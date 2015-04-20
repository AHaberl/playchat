package helper;

import models.Configuration;

public class ServerHelper {

	
	public static String generateUID(){
		
		String UID = "";	
		
		UID += Configuration.getServerName();
		UID += System.currentTimeMillis();
		
		return UID;
	}
}

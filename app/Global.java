

import java.util.HashSet;
import java.util.Set;

import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import controllers.ServerController;
import controllers.UserController;

public class Global extends GlobalSettings {
	
    @Override
    public void onStart(Application app) {
    	Logger.info("Application has started");
    	
    	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
    	jedisClusterNodes.add(new HostAndPort("127.0.0.1", 30003));
    	ServerController.cluster = new JedisCluster(jedisClusterNodes);
    	
    	UserController.registerUser(new User("Mario", "supersafe"));
    	UserController.registerUser(new User("Bowser", "supersafe"));
    	UserController.registerUser(new User("Peach", "supersafe"));
    	
    	System.out.println("init");
    }

    public void onStop(Application app) {
    	Logger.info("user");
    }

}


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
    	
    	User andi = new User();
    	andi.setUserName("Andi");
    	andi.setID("1");
    	andi.setPassword("supersafe");
    	andi.setStatus("");
    	
    	ServerController.cluster.hset("users", andi.getUserName(), andi.getID());
    	ServerController.cluster.hmset("user:" + 1, UserController.convertUser(andi));
    	
    	System.out.println("init");
    }

    public void onStop(Application app) {
    	Logger.info("user");
    }

}
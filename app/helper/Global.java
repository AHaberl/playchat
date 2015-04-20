package helper;
import java.util.HashSet;
import java.util.Set;

import controllers.UserController;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class Global extends GlobalSettings {
    public static JedisCluster cluster;
	
    public void onStart(Application app) {
    	Logger.info("Application has started");
    	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
    	jedisClusterNodes.add(new HostAndPort("127.0.0.1", 30001));
    	JedisCluster cluster = new JedisCluster(jedisClusterNodes);
    	User benny = new User();
    	Long nextID = cluster.incr("userid");
    	benny.setPassword("supersafe");
    	benny.setUserName("Andi");
    	benny.setStatus("");
    	benny.setID(nextID.toString());
    	cluster.set("users", benny.getID());
    	cluster.hmset("user:"+benny.getID(), UserController.convertUser(benny));
    }

    public void onStop(Application app) {
    	Logger.info("user");
    }

}
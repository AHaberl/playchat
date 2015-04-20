

import java.util.HashSet;
import java.util.Set;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import controllers.ServerController;

public class Global extends GlobalSettings {
	
    @Override
    public void onStart(Application app) {
    	Logger.info("Application has started");
    	
    	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
    	jedisClusterNodes.add(new HostAndPort("127.0.0.1", 30001));
    	ServerController.cluster = new JedisCluster(jedisClusterNodes);
    	System.out.println("init");
    }

    public void onStop(Application app) {
    	Logger.info("user");
    }

}
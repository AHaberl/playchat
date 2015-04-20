import java.util.HashSet;
import java.util.Set;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class Global extends GlobalSettings {
	private static Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
   // public static JedisCluster cluster;
	
    public void onStart(Application app) {
//    	Logger.info("Application has started");
//    	jedisClusterNodes.add(new HostAndPort("127.0.0.1", 6379));
//    	JedisCluster cluster = new JedisCluster(jedisClusterNodes);
//    	cluster.set("user", "Benny");    	
    }

    public void onStop(Application app) {
//    	String user = cluster.hget("users", "benny");
    	Logger.info("user");
    }

}
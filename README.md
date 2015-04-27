## .chat
Simple chat application with Redis, Jedis and Play written in Java.
### Step by Step setup
* Download and make Redis version >= 3.0: https://redis.io/download
* Download and setup Play Framework with Typesafe Activator: https://www.playframework.com/documentation/2.3.x/Installing
* Clone this Repository
* From inside the repository 
  % activator compile
* Start the Redis-Cluster with 
  % ./cluster-create start
  % ./cluster-create create
* from within the folder redis/utils/create-cluster/
* For additional instructions check https://redis.io/topics/cluster-tutorial
* Start the server with
  % activator run
* Goto localhost:9000/view/login and register or use one of the sample users Peach, Mario or Bowser with password "supersafe" to login
* You can start additional app-servers by using
  % activator "run xxxx" 
* where xxxx is the port number

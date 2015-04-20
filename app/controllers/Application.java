package controllers;

import play.mvc.Controller;
import play.mvc.Result;


public class Application extends Controller {

    public static Result index() {
    	String id = "nulle";
    	id = ServerController.cluster.hget("users", "Andi");
        return ok(id);
    }
}

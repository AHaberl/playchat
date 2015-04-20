package controllers;



import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class ViewController extends Controller{

	
	public static Result login(){
		return ok(login.render(""));
	}
	
	public static Result chat(){
		return ok();
	}
}

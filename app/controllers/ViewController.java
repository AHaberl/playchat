package controllers;



import play.mvc.Controller;
import play.mvc.Result;


public class ViewController extends Controller{

	
	public static Result login(){
		return views.html.ViewController.login.render("localhost:9000/login", "localhost:9000/chat");
	}
	
	public static Result chat(){
		return ok();
	}
}

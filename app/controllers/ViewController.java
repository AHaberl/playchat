package controllers;



import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class ViewController extends Controller{

	
	public static Result login(){
		if(null == session("status") || "".equals(session("status"))){
			return ok(login.render());
		}
		return redirect(routes.ViewController.chat());
	}
	
	public static Result chat(){
		if(null == session("status")  || "".equals(session("status"))){
			return redirect(routes.ViewController.login());
		}
		return ok(chat.render(session("username")));
	}
}

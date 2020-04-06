package dtu.library.app;
import java.util.*;
public class View{
	private Model model;
	private Controller controller;
	public View() {
		this.model = new Model(this);
		this.controller = new Controller(this, model);
//		startup();
	}
	
	
	public void showMessage(String message) {
		System.out.println(message);
		System.out.println();
	}
	
	public void startup() {
		while(true) {
			String nextCommand = controller.getCommand();
			controller.runCommand(nextCommand);
		}
	}


}

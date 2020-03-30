package dtu.library.app;
import java.util.*;
public class View{
	private Model model;
	private Controller controller;
	public View(Model model, Controller controller) {
		this.model = new Model(this);
		this.controller = new Controller(this, model);
	}
	
	
	public void showMessage(String message) {
		System.out.println(message);
		System.out.println();
	}
	public static void main(String[] args) {

		while(true) {
			int nextCommand = controller.getCommand();
			controller.runCommand(nextCommand);
		}
	}


}

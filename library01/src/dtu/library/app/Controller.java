package dtu.library.app;
import java.util.*;
public class Controller {
	private Model model;
	private View view;
	Scanner scanner;
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		scanner = new Scanner(System.in);
	}
	int getCommand() {
		return scanner.nextInt();
	}
	public void runCommand(int nextCommand) {
		if(nextCommand == 1) {
			model.createProject();
		}
		if(nextCommand == 2) {
			view.showMessage("Please enter the id of the project");
			String userInput = getInput();
			model.getProject(userInput).toString();
		}
		
	}
	
	public String getInput() {
		return scanner.nextLine();
	}
}

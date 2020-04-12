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

	String getCommand() {
		return scanner.next();
	}

	public void runCommand(String nextCommand) {
		if(nextCommand.equals("Create Project")) {
//			model.createProject();
		}
		if(nextCommand.equals("Get Project")) {
			view.showMessage("Please enter the id of the project");
			String userInput = getInput();
			System.out.println(model.getProject(userInput).toString());
		}
	}

//	public boolean exists(){
//		return model.exists();
//	}

	public Project getProject(){
		return model.getNewProject(); 
	}

	public String getInput() {
		return scanner.next();
	}
}

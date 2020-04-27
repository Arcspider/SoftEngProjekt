package dtu.library.app;

import java.util.*;

public class Controller {
	private Model model;
	private View view;
	private Project newProject;
	Scanner scanner;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		scanner = new Scanner(System.in);
	}

	String getCommand() {
		return scanner.next();
	}

	public void runCommand(String nextCommand) throws OperationNotAllowedException {
		if (nextCommand.equals("Create")) {
			System.out.println("Name the Project: ");
			String name = getCommand();
			newProject = createProject(name);
			  if(checkName(name)) {
			    	addProject(newProject);
			  }
		} else if (nextCommand.equals("Get")) {
			model.changeStage("Project");
		} else if (nextCommand.equals("Worker")) {
			model.changeStage("Worker");
		}
		
	}


	public Project createProject(String name) throws OperationNotAllowedException {
		return model.createProject(name);
	}

	public boolean exists(String id) {
		return model.hasID(id);
	}

	public boolean checkName(String name) throws OperationNotAllowedException {
		return model.checkName(name);
	}

	public void addProject(Project project) throws OperationNotAllowedException {
		model.addProject(project);	
	}

}

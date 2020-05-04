package controllerInterface;

import java.util.*;

import projectManagerObjects.ModelApplication;
import projectManagerObjects.ModelProject;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;

public class ControllerApplication {
	private ModelApplication modelApplication;
	private ModelProject modelProject;
	private View view;
	private Project newProject;
	Scanner scanner;

	public ControllerApplication(View view, ModelApplication modelApplication, ModelProject modelProject ) {
		this.view = view;
		
		this.modelApplication = modelApplication;
		this.modelProject = modelProject;
		scanner = new Scanner(System.in);
	}

	public String getCommand() {
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
			modelApplication.changeStage("Project");
			
		} else if (nextCommand.equals("Exit")) {
			System.exit(0);
			
		} else if (nextCommand.equals("Worker")) {
			modelApplication.changeStage("Worker");
			view.showAvailableCommands(modelApplication.getStage());
		}
	}


	private Project createProject(String name) throws OperationNotAllowedException {
		return modelProject.createProject(name);
	}

	private boolean hasID(String id) {
		return modelProject.hasID(id);
	}

	private boolean checkName(String name) throws OperationNotAllowedException {
		return modelProject.checkName(name);
	}

	private void addProject(Project project) throws OperationNotAllowedException {
		modelProject.addProject(project);	
	}
}

package dtu.library.app.controllerInterface;

import java.util.*;

import dtu.library.app.ModelApplication;
import dtu.library.app.ModelProject;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

public class ControllerApplication {
	private ModelApplication modelAplication;
	private ModelProject modelProject;
	private View view;
	private Project newProject;
	Scanner scanner;

	public ControllerApplication(View view, ModelApplication modelAplication, ModelProject modelProject ) {
		this.view = view;
		
		this.modelAplication = modelAplication;
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
			modelAplication.changeStage("Project");
			
		} else if (nextCommand.equals("Exit")) {
			System.exit(0);
			
		} else if (nextCommand.equals("Worker")) {
			modelAplication.changeStage("Worker");
			view.showAvailableCommands(modelAplication.getStage());
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

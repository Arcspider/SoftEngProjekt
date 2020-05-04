package controllerInterface;

import java.util.*;

import applicationManagerInterface.applicationManager;
import applicationManagerInterface.projectManager;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;

public class ControllerApplication {
	private applicationManager modelApplication;
	private projectManager modelProject;
	private View view;
	private Project newProject;
	Scanner scanner;

	public ControllerApplication(View view, applicationManager modelApplication, projectManager modelProject ) {
		this.view = view;
		
		this.modelApplication = modelApplication;
		this.modelProject = modelProject;
		scanner = new Scanner(System.in);
	}

	public String getCommand() {
		return scanner.next();
	}

	public void runCommand(String nextCommand) throws OperationNotAllowedException {
		switch (nextCommand) {
			case "Create":
				view.showMessage("Name the Project: ");
				String name = getCommand();
				newProject = createProject(name);
				if (checkName(name)) {
					addProject(newProject);
					view.showAvailableCommands(modelApplication.getStage());
				}
				break;
			case "Get":
				modelApplication.changeStage("Project");

				break;
			case "Exit":
				System.exit(0);

			case "Worker":
				modelApplication.changeStage("Worker");
				view.showAvailableCommands(modelApplication.getStage());
				break;
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

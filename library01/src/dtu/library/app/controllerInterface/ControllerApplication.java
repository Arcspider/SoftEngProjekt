package dtu.library.app.controllerInterface;

import java.util.*;

import dtu.library.app.ModelApplication;
import dtu.library.app.ModelProject;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

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

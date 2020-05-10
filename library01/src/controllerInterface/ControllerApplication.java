package controllerInterface;
//Tobias(194616), Søren(s194630), Jakob(s194623) har haft ansvaret for denne klasse.
import java.util.*;

import applicationManagerInterface.ApplicationManager;
import applicationManagerInterface.ProjectManager;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;

public class ControllerApplication {
	private ApplicationManager modelApplication;
	private ProjectManager modelProject;
	private View view;
	private Project newProject;
	Scanner scanner;

	public ControllerApplication(View view, ApplicationManager modelApplication, ProjectManager modelProject ) {
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
				view.showMessage("Shutting down");
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

	private boolean checkName(String name) throws OperationNotAllowedException {
		return modelProject.checkName(name);
	}

	private void addProject(Project project) throws OperationNotAllowedException {
		modelProject.addProject(project);	
	}
}

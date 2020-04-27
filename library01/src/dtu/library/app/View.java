package dtu.library.app;

import java.util.*;

import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;

public class View {
	private Model model;
	private Controller controller;
	private ControllerProject controllerProject;
	private ControllerActivity controllerActivity;

	public View() throws OperationNotAllowedException {
		this.model = new Model(this);
		this.controller = new Controller(this, model);
		this.controllerProject = new ControllerProject(this,model);
		this.controllerActivity = new ControllerActivity(this,model);
		showApplicationIntroduction();
		startup();
	}

	public void showMessage(String message) {
		System.out.println(message);
	}
	
	public void showAvailableCommands(String stage) {
		if(stage.equals("Application")) {
		showApplicationIntroduction();
		} else if(stage.equals("Project")) {
			showProjectIntroduction(model.getThisProject());
		} else if(stage.equals("Activity")) {
			
		}
	}

	public void startup() throws OperationNotAllowedException {
		while (true) {
			while (model.getStage().equals("Application")) {
				String nextCommand = controller.getCommand();
				controller.runCommand(nextCommand);
			}
			while (model.getStage().equals("Project")) {
				controllerProject.runCommand();
			}
			while(model.getStage().equals("Activity")) {
				controllerActivity.runCommand();
			}
		}
	}
	
	private void showApplicationIntroduction() {
		System.out.println("Current available commands: Create, Get");
		System.out.println("Create: Creates new project");
		System.out.println("Get: Access existing project");
	}

	public void showProjectIntroduction(Project project) {
		System.out.println(project.toString());
		System.out.println();
		System.out.println("Current available commands: Name, Description, Remove, Add, Edit, Back");
		System.out.println("Name: Change the name of this project");
		System.out.println("Description: Change the description of this project");
		System.out.println("Remove: Remove this project. WARNING: Once removed, this project is permanently inaccessible");
		System.out.println("Add: Adds an activity to this project");
		System.out.println("Edit: Access and edit a specific activity in this project");
		System.out.println("Back: Exit this project");
	}
}

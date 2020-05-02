package dtu.library.app;

import java.util.*;

import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;
import dtu.library.app.controllerInterface.ControllerWorker;

public class View {
	private Model model;
	private Controller controller;
	private ControllerProject controllerProject;
	private ControllerActivity controllerActivity;
	private ControllerWorker controllerWorker;

	public View() throws OperationNotAllowedException {
		this.model = new Model(this);
		this.controller = new Controller(this, model);
		this.controllerProject = new ControllerProject(this,model);
		this.controllerActivity = new ControllerActivity(this,model);
		showApplicationIntroduction();
		//startup();
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
			
		} else if(stage.equals("Worker")) {
			showWorkerIntroduction();
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
			while(model.getStage().equals("Worker")) {
				controllerWorker.runCommand();
			}
		}
	}

	private void showApplicationIntroduction() {
		System.out.println("Current available commands: Create, Get, Worker, Exit");
		System.out.println("Create: Creates new project");
		System.out.println("Get: Access existing project");
		System.out.println("Worker: Gain access to worker-related commands");
		System.out.println("Exit: Close the program");
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
		System.out.println("Leader: Assign a leader to this project");
		System.out.println("Back: Exit this project");
	}
	
	private void showWorkerIntroduction() {
		System.out.println("Current available commands: Create");
		System.out.println("Create: Add a new employee to the database");
	}
}

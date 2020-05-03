package dtu.library.app;

import java.util.*;

import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;
import dtu.library.app.controllerInterface.ControllerWorker;

public class View {

	private ModelApplication modelAplication;
	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ModelWorker modelWorker;
	private ControllerApplication controller;
	private ControllerProject controllerProject;
	private ControllerActivity controllerActivity;
	private ControllerWorker controllerWorker;

	public View() throws OperationNotAllowedException {
		this.modelAplication = new ModelAplication(this);
		this.modelWorker = new ModelWorker();
		this.modelProject = new ModelProject(this);
		this.modelActivity = new ModelActivity(this);
		
		this.controller = new ControllerApplication(this, modelAplication, modelProject);
		this.controllerProject = new ControllerProject(this, modelAplication, modelProject, modelActivity,modelWorker);
		this.controllerActivity = new ControllerActivity(this, modelAplication, modelProject, modelActivity);
		this.controllerWorker = new ControllerWorker(this,modelWorker);
		showApplicationIntroduction();
		// startup();
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public void showAvailableCommands(String stage) {
		if (stage.equals("Application")) {
			showApplicationIntroduction();
		} else if (stage.equals("Project")) {
			showProjectIntroduction(modelProject.getThisProject());
		} else if (stage.equals("Activity")) {
			showActivityIntroduction();
		} else if (stage.equals("Worker")) {
			showWorkerIntroduction();
		}
	}


	public void startup() throws OperationNotAllowedException {
		while (true) {
			while (getStage().equals("Application")) {
				String nextCommand = controller.getCommand();
				controller.runCommand(nextCommand);
			}
			while (getStage().equals("Project")) {
				controllerProject.runCommand();
			}
			while (getStage().equals("Activity")) {
				controllerActivity.runCommand();
			}
			while (getStage().equals("Worker")) {
				controllerWorker.runCommand();
			}
		}
	}

	private String getStage() {
		return modelAplication.getStage();
	}

	private void showApplicationIntroduction() {
		System.out.println("Current available commands: Create, Get, Worker, Exit");
		System.out.println("Create: Creates new project");
		System.out.println("Get: Access existing project");
		System.out.println("Worker: Gain access to worker-related commands");
		System.out.println("Exit: Close the program");
	}

	public void showProjectIntroduction(Project project) {
		System.out.println();
		System.out.println("Current available commands: Name, Description, Time, Remove, Add, Edit, Back");
		System.out.println("Name: Change the name of this project");
		System.out.println("Description: Change the description of this project");
		System.out.println("Time: Change the start and end dates of the project");
		System.out.println(
				"Remove: Remove this project. WARNING: Once removed, this project is permanently inaccessible");
		System.out.println("Add: Adds an activity to this project");
		System.out.println("Edit: Access and edit a specific activity in this project");
		System.out.println("Leader: Assign a leader to this project");
		System.out.println("Back: Exit this project");
	}
	
	private void showActivityIntroduction() {
		System.out.println("Current available commands: Time, Back");
		System.out.println("Time: Change the start and end dates of the activity");
		System.out.println("Back: Exit back to the project");
	}

	private void showWorkerIntroduction() {
		System.out.println("Current available commands: Create, Back");
		System.out.println("Create: Add a new employee to the database");
		System.out.println("Back: Exit back to the main application");
	}
}

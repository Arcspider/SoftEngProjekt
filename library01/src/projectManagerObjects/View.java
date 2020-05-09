package projectManagerObjects;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ApplicationManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import controllerInterface.ControllerActivity;
import controllerInterface.ControllerApplication;
import controllerInterface.ControllerProject;
import controllerInterface.ControllerWorker;

public class View {

	private ApplicationManager modelApplication;
	private ProjectManager modelProject;
	private ActivityManager modelActivity;
	private WorkerManager modelWorker;
	private ControllerApplication controller;
	private ControllerProject controllerProject;
	private ControllerActivity controllerActivity;
	private ControllerWorker controllerWorker;

	public View() throws OperationNotAllowedException {
		this.modelApplication = new ApplicationManager(this);
		this.modelWorker = new WorkerManager();
		this.modelProject = new ProjectManager(this);
		this.modelActivity = new ActivityManager(this);
		
		this.controller = new ControllerApplication(this, modelApplication, modelProject);
		this.controllerProject = new ControllerProject(this, modelApplication, modelProject, modelActivity,modelWorker);
		this.controllerActivity = new ControllerActivity(this, modelApplication, modelProject, modelActivity, modelWorker);
		this.controllerWorker = new ControllerWorker(this,modelWorker, modelApplication);
	}

	public void showMessage(String message) {
		System.out.println(message);
	}

	public void showAvailableCommands(String stage) {
		switch (stage) {
			case "Application":
				showApplicationIntroduction();
				break;
			case "Project":
				showProjectIntroduction(modelProject.getThisProject());
				break;
			case "Activity":
				showActivityIntroduction();
				break;
			case "Worker":
				showWorkerIntroduction();
				break;
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
		return modelApplication.getStage();
	}

	public void showApplicationIntroduction() {
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
		System.out.println("Current available commands: Date, Time, Assign, List, Shift, Back");
		System.out.println("Date: Change the start and end dates of this activity, or adjust the budgetted hours on the activity");
		System.out.println("Assign: Assign an employee to this activity");
		System.out.println("List: List all workers assigned to this activity");
		System.out.println("View: List all shifts asigned to this activity");
		System.out.println("Shift: Choose a worker and add some work hours to the activity");
		System.out.println("RemoveShift: Choose a worker and remove their shift from the activity");
		System.out.println("Back: Exit back to the project");
	}

	private void showWorkerIntroduction() {
		System.out.println("Current available commands: Create, Absence, Returned, Back");
		System.out.println("Create: Add a new employee to the database");
		System.out.println("Absence: Specify a time interval in which a worker is not able to work");
		System.out.println("Returned: Inform the system that an employee is no longer absent");
		System.out.println("Back: Exit back to the main application");
	}
}

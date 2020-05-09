package controllerInterface;

import java.util.Scanner;

import applicationManagerInterface.ApplicationManager;
import applicationManagerInterface.WorkerManager;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.View;
import projectManagerObjects.Worker;

public class ControllerWorker {
	private WorkerManager modelWorker;
	private ApplicationManager modelApplication;	
	private View view;
	Scanner scanner;
	public ControllerWorker(View view, WorkerManager modelWorker, ApplicationManager modelApplication) {
		this.view = view;
		this.modelWorker = modelWorker;
		scanner = new Scanner(System.in);
		this.modelApplication = modelApplication;
	}

	public void runCommand() throws OperationNotAllowedException {
		String nextCommand = getCommand();
		if (nextCommand.equals("Create")) {
			view.showMessage("Please enter the new employee's first name");
			String fristname = getCommand();
			view.showMessage("Please enter the new employee's last name");
			String lastname = getCommand();
			view.showMessage(modelWorker.createWorker(fristname, lastname).toString());
		
		}else if(nextCommand.equals("Absence")) {
			String id = requestWorkerId();
			view.showMessage("Please clarify when they will start being absent");
			view.showMessage("the date format: dd-mm-yyyy");
			String startAbsence = getCommand();
			view.showMessage("Please clarify when they will be back and ready to work");
			view.showMessage("the date format: dd-mm-yyyy");
			String endAbsence = getCommand();
			modelWorker.assignAbsence(id, startAbsence, endAbsence);
			getThisWorker().setAbsence(true);
			view.showMessage("Absent days noted");
			view.showAvailableCommands(modelApplication.getStage());
			
		}else if(nextCommand.equals("Returned")) {
			requestWorkerId();
			if(getThisWorker().getAbsence()) {
				getThisWorker().setAbsence(false);
				view.showMessage("Welcome back to work!");
			} else {
				view.showMessage("This employee is already pressent");
			}
			
		}else if(nextCommand.equals("Back")) {
			modelApplication.changeStage("Application"); 
			view.showAvailableCommands(modelApplication.getStage());
		}
	}

	private String requestWorkerId() {
		view.showMessage("Please enter the ID of employee");
		String userId = getCommand();
		setWorker(userId);
		return userId;
	}

	private void setWorker(String id) {
		modelWorker.setThisWorker(id);
	}

	private Worker getThisWorker() {
		return modelWorker.getThisWorker();
	}

	public Worker createWorker(String fristname, String lastname) {
		return modelWorker.createWorker(fristname, lastname);
	}

	private String getCommand() {
		return scanner.next();
	}

	public boolean workerHasID(String id) {
		return modelWorker.workerHasID(id);
	}

}

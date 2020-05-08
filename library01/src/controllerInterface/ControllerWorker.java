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
	private boolean hasWorker;

	public ControllerWorker(View view, WorkerManager modelWorker, ApplicationManager modelApplication) {
		this.view = view;
		hasWorker = false;
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

		}else if(nextCommand.equals("Get")) {
			view.showMessage("Please enter the ID of the employee");
				
			setHasWorker(true);
			
		}else if(nextCommand.equals("Back")) {
			modelApplication.changeStage("Application"); 
			view.showAvailableCommands(modelApplication.getStage());
		}
	}

	private void setHasWorker(boolean hasWorker) {
		this.hasWorker = hasWorker;
	}
	
	private boolean getHasWorker() {
		return hasWorker;
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

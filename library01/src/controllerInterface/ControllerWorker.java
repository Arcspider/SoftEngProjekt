package controllerInterface;

import java.util.Scanner;

import applicationManagerInterface.applicationManager;
import applicationManagerInterface.workerManager;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.View;
import projectManagerObjects.Worker;

public class ControllerWorker {
	private workerManager modelWorker;
	private applicationManager modelApplication;	
	private View view;
	Scanner scanner;

	public ControllerWorker(View view, workerManager modelWorker, applicationManager modelApplication) {
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

		}else if(nextCommand.equals("Back")) {
			modelApplication.changeStage("Application"); 
			view.showAvailableCommands(modelApplication.getStage());
		}
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

package controllerInterface;

import java.util.Scanner;

import projectManagerObjects.ModelApplication;
import projectManagerObjects.ModelWorker;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.View;
import projectManagerObjects.Worker;

public class ControllerWorker {
	private ModelWorker modelWorker;
	private ModelApplication modelApplication;	
	private View view;
	Scanner scanner;

	public ControllerWorker(View view, ModelWorker modelWorker, ModelApplication modelApplication) {
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

package dtu.library.app.controllerInterface;

import java.util.Scanner;

import dtu.library.app.Model;
import dtu.library.app.ModelWorker;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.View;
import dtu.library.app.Worker;

public class ControllerWorker {
	private Model model;
	private ModelWorker modelWorker;
	private View view;
	private Worker worker;
	Scanner scanner;

	public ControllerWorker(View view, Model model,ModelWorker modelWorker) {
		this.view = view;
		this.model = model;
		this.modelWorker =modelWorker;
		scanner = new Scanner(System.in);
	}

	public void runCommand() throws OperationNotAllowedException {
		String nextCommand = getCommand();
		if (nextCommand.equals("Create")) {
			view.showMessage("Please enter the new employee's first name and last name");
			String fristname = getCommand();
			String lastname = getCommand();
			modelWorker.createWorker(fristname, lastname).toString();

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

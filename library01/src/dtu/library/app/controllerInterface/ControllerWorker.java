package dtu.library.app.controllerInterface;

import java.util.Scanner;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.View;
import dtu.library.app.Worker;

public class ControllerWorker {
	private Model model;
	private View view;
	private Worker worker;
	Scanner scanner;

	public ControllerWorker(View view, Model model) {
			this.view = view;
			this.model = model;
			scanner = new Scanner(System.in);
		}

	public void runCommand() throws OperationNotAllowedException {
		String nextCommand = getCommand();
		if (nextCommand.equals("Create")) {
			String fristname = getCommand();
			String lastname = getCommand();
			createWorker(fristname, lastname);

		}
	}

	public Worker createWorker(String fristname, String lastname) {
		return model.createWorker(fristname, lastname);
	}

	private String getCommand() {
		return scanner.next();
	}

	public boolean workehasID(String id) {
		return model.workeHasID(id);
	}

}

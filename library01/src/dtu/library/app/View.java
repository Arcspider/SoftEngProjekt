package dtu.library.app;

import java.util.*;

import dtu.library.app.controllerInterface.ControllerProject;

public class View {
	private Model model;
	private Controller controller;
	private ControllerProject controllerProject;

	public View() throws OperationNotAllowedException {
		this.model = new Model(this);
		this.controller = new Controller(this, model);
		this.controllerProject = new ControllerProject(this,model);
		startup();
	}

	public void showMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	public void startup() throws OperationNotAllowedException {
		while (true) {
			while (model.getStage().equals("application")) {
				String nextCommand = controller.getCommand();
				controller.runCommand(nextCommand);
			}
			while (model.getStage().equals("project")) {
				controllerProject.runCommand();
			}
		}
	}

}

package dtu.library.app;

import java.util.*;

import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;

public class View {
	private Model model;
	private Controller controller;
	private ControllerProject controllerProject;
	private ControllerActivity controllerActivity;

	public View() throws OperationNotAllowedException {
		this.model = new Model(this);
		this.controller = new Controller(this, model);
		this.controllerProject = new ControllerProject(this,model);
		this.controllerActivity = new ControllerActivity(this,model);
		startup();
	}

	public void showMessage(String message) {
		System.out.println(message);
		System.out.println();
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
		}
	}
}

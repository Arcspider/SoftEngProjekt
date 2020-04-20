package dtu.library.app.controllerInterface;

import java.util.Scanner;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

public class ControllerActivity {
	private Model model;
	private View view;
	Scanner scanner;

	public ControllerActivity(View view, Model model) {
		this.view = view;
		this.model = model;
		scanner = new Scanner(System.in);
	}

	public void runCommand() {
		

	}

	public void addProject(Project project) throws OperationNotAllowedException {
		model.addProject(project);
	}

	public boolean hasProject(String string) {

		return model.hasID(string);
	}

	public boolean addActivity(String string, Project project) throws OperationNotAllowedException {
		return model.addActivity(string, project);

	}

	public boolean hasActivity(String sA, String sP) {
		return model.hasActivity(sA, sP);
	}

}

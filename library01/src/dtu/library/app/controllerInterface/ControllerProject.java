package dtu.library.app.controllerInterface;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerProject {
	private Model model;
	private View view;
	private Project project;
	private boolean hasProject = false;
	Scanner scanner;

	public ControllerProject(View view, Model model) {
		this.view = view;
		this.model = model;
		scanner = new Scanner(System.in);
	}

	public String getCommand() {
		return scanner.next();
	}

	public void runCommand() throws OperationNotAllowedException {
		if (!hasProject) {
			view.showMessage("Please enter the id of the project");
			String id = getCommand();
			if (!exists(id)) {
				model.changeStage("Application");
				System.out.println("Project does not exist. Commands: Get or Create");
			} else {
				hasProject = true;
				project = model.getProject(id);
				System.out.println(project.toString());
			}
		}else if (hasProject) {
			System.out.println("project commands");
			String nextCommand = getCommand();
			if (nextCommand.equals("Description")) {
				editProjectDescription(project, getDescription());
			} else if (nextCommand.equals("Name")) {
				editProjectName(project, getCommand());
				System.out.println(project.toString());
			}
		}
	}

	private String getDescription() {
		return scanner.nextLine();
	}

	public void removeProject(Project project) throws OperationNotAllowedException {
		model.removeProject(project);

	}

	public boolean exists(String ID) {
		return model.hasID(ID);
	}

	public Project getProject() {
		return model.getNewProject();
	}

	public boolean checkName(String name) throws OperationNotAllowedException {
		return model.checkName(name);
	}

	public boolean editProjectDescription(Project project, String description) {
		return model.editProjectDescription(project, description);
	}

	public ArrayList<Project> getProjects() {
		return model.getProjects();
	}

	public boolean editProjectName(Project project, String name) {
		return model.editProjectName(project, name);

	}
}

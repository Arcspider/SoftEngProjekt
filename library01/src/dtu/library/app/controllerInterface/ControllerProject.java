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
				project = model.getProject(id);
				System.out.println(model.getProject(getCommand()).toString());
			}
		}
		String nextCommand = getCommand();
		if(nextCommand.equals("Description")) {
			editProjectDescription(project,scanner.nextLine());
		}
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

	public Project getOldProject(String ID) throws OperationNotAllowedException {
		return model.getProject(ID);
	}


	public boolean checkName(String name) throws OperationNotAllowedException {
		return model.checkName(name);
	}

	public boolean editProjectDescription(Project project, String description) throws OperationNotAllowedException {
		return model.editProjectDescription(project, description);
	}

	public ArrayList<Project> getProjects() {
		return model.getProjects();
	}

	public boolean editProjectName(String ID, String name) {
		return model.editProjectName(ID, name);

	}
}

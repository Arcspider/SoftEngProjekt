package dtu.library.app.controllerInterface;

import dtu.library.app.ModelActivity;
import dtu.library.app.ModelAplication;
import dtu.library.app.ModelProject;
import dtu.library.app.ModelWorker;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
//import dtu.library.app.Worker;
import dtu.library.app.Worker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerProject {

	private ModelAplication modelAplication;
	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ModelWorker modelWorker;
	private View view;

	Scanner scanner;
	Scanner descriptionHandler;

	public ControllerProject(View view, ModelAplication modelAplication, ModelProject modelProject,
			ModelActivity modelActivity, ModelWorker modelWorker) {
		this.view = view;
		this.modelAplication = modelAplication;
		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.modelWorker = modelWorker;
		scanner = new Scanner(System.in);
		descriptionHandler = new Scanner(System.in);
	}

	public String getCommand() {
		return scanner.next();
	}

	public void runCommand() throws OperationNotAllowedException {
		if (!getHasProject()) {
			view.showMessage("Please enter the id of the project");
			String id = getCommand();
			if (!exists(id)) {
				changeStage("Application");
				view.showMessage("Project does not exist. Commands: Create, Get");
			} else {
				setHasProject(true);
				setThisProject(id);

			}
		} else {
			view.showAvailableCommands(modelAplication.getStage());
			String nextCommand = getCommand();
			if (nextCommand.equals("Description")) {
				view.showMessage("Please enter a desired description");
				editProjectDescription(getThisProject(), getDescription());

			} else if (nextCommand.equals("Name")) {
				view.showMessage("Please enter the new name");
				editProjectName(getThisProject(), getCommand());

			} else if (nextCommand.equals("Remove")) {
				removeProject(getThisProject());
				setHasProject(false);
				view.showMessage("The project has been removed. Available commands: Create, Get");
				modelAplication.changeStage("Application");
			} else if (nextCommand.equals("Add")) {
				view.showMessage("Please enter a name for the activity");
				addActivity(getThisProject(), getCommand());

			} else if (nextCommand.equals("Edit")) {
				changeStage("Activity");

			} else if (nextCommand.equals("Time")) {
				view.showMessage("Type \"Start\" to change the start date of the project");
				view.showMessage("Type \"End\" to change the end date of the project");

				nextCommand = getCommand();
				if (nextCommand.equals("Start")) {
					view.showMessage("Write the new start date in the format: ");
					setProjectStart(getThisProject(), descriptionHandler.nextLine());
					System.out.println(getThisProject().toString());
				} else if (nextCommand.equals("End")) {
					view.showMessage("Write the new end date in the format: ");
					setProjectEnd(getThisProject(), descriptionHandler.nextLine());
					System.out.println(getThisProject().toString());
				}

			} else if (nextCommand.equals("Leader")) {
				view.showMessage("Please enter the id of worker you want to lead this project");
				nextCommand = getCommand();
				if (modelWorker.workerHasID(nextCommand)) {
					modelProject.setLeader(getThisProject(), getWorker(nextCommand));
				}
			} else if (nextCommand.equals("Back")) {
				changeStage("Application");
				setHasProject(false);
				view.showAvailableCommands(modelAplication.getStage());
			}
		}
	}

	private Worker getWorker(String id) {
		return modelWorker.getWorker(id);
	}

	private void addActivity(Project project, String name) throws OperationNotAllowedException {
		modelActivity.addActivity(project, name);

	}

	private boolean getHasProject() {
		return modelProject.getHasProject();
	}

	private Project getThisProject() {
		return modelProject.getThisProject();
	}

	private void setThisProject(String id) {
		modelProject.setThisProject(id);

	}

	private void setHasProject(boolean is) {
		modelProject.setHasProject(is);

	}

	private void changeStage(String stage) {
		modelAplication.changeStage(stage);

	}

	private String getDescription() {
		return descriptionHandler.nextLine();
	}

	public void removeProject(Project project) throws OperationNotAllowedException {
		modelProject.removeProject(project);

	}

	public boolean exists(String ID) {
		return modelProject.hasID(ID);
	}

	public boolean editProjectDescription(Project project, String description) {
		return modelProject.editProjectDescription(project, description);
	}

	public boolean editProjectName(Project project, String name) {
		return modelProject.editProjectName(project, name);

	}

	public ArrayList<Project> getProjects() {
		return modelProject.getProjects();
	}

	public LocalDate getProjectStart(Project project) {
		return modelProject.getProjectStart(project);
	}

	public LocalDate getProjectEnd(Project project) {
		return modelProject.getProjectEnd(project);
	}

	public boolean validDate(String someDate) {
		return modelProject.verifyDateFormat(someDate);
	}

	public void setProjectStart(Project project, String startDate) {
		modelProject.setProjectStart(project, startDate);
	}

	public void setProjectEnd(Project project, String endDate) {
		modelProject.setProjectEnd(project, endDate);
	}
}

package controllerInterface;

import java.util.Scanner;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ApplicationManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;

public class ControllerActivity {
	private ApplicationManager modelApplication;
	private ProjectManager modelProject;
	private ActivityManager modelActivity;
	private WorkerManager modelWorker;
	private View view;
	Scanner scanner;
	Scanner timeHanlder;

	public ControllerActivity(View view, ApplicationManager modelApplication, ProjectManager modelProject,
			ActivityManager modelActivity, WorkerManager modelWorker) {
		this.view = view;
		this.modelApplication = modelApplication;
		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.modelWorker = modelWorker;
		scanner = new Scanner(System.in);
		timeHanlder = new Scanner(System.in);
	}

	public void runCommand() throws OperationNotAllowedException {
		if (!hasActivity()) {
			String name = getCommand();
			if (!activityExists(getThisProject(), name)) {
				changeStage("Project");
				view.showMessage("This project " + getThisProject().getId() + " does not have the activity " + name);
			} else {
				setHasActivity(true);
				setThisActivity(getActivity(getThisProject(), name));
				getThisActivity().toString();
			}
		} else {
			view.showAvailableCommands(modelApplication.getStage());
			String nextCommand = getCommand();
			switch (nextCommand) {
			case "Date":
				view.showMessage("Type \"Start\" to change the start date of the project");
				view.showMessage("Type \"End\" to change the end date of the project");
				view.showMessage("Type \"Budget\"to add to budgetted hours");
				view.showMessage("The date format is \"ww-yyyy\" where ww is week and yyyy is year");
				view.showMessage("The hours must be added in increments of 0.5");
				nextCommand = getCommand();
				switch (nextCommand) {
				case "Start":
					view.showMessage("Write the new start date in the format: ww-yyyy");
					setActivityStart(getThisProject(), getThisActivity(), timeHanlder.nextLine());

					break;
				case "End":
					view.showMessage("Write the new end date in the format: ww-yyyy");
					setActivityEnd(getThisProject(), getThisActivity(), timeHanlder.nextLine());
					break;
				case "Budget":
					view.showMessage("Please input the additional budgetted hours in increments of 0.5");

					break;

				}
				break;
			case "Back":
				setHasActivity(false);
				changeStage("Project");

				break;
			case "Assign":
				view.showMessage("Please enter the ID of the employee you want to assign to this activity");
				nextCommand = getCommand();
				if (modelWorker.workerHasID(nextCommand)) {
					getThisActivity().assignWorker(modelWorker.getWorker(nextCommand));
				}
				break;
			case "Time":
				view.showMessage("Please supply the following information");
				view.showMessage("WorkerID: ");
				String workerID = getCommand();
				view.showMessage("Date (in the format: dd-mm-yyyy): ");
				String date = getCommand();
				view.showMessage("Time: ");
				String time = getCommand();
				modelActivity.addShift(getThisActivity(), workerID, date, time);
				break;
			case "List":
				getThisActivity().listWorkers();
				break;
			case "Check":
				getThisActivity().getBudgettedHours();
				break;
			}
		}
	}

	private Activity getThisActivity() {
		return modelActivity.getThisActivity();
	}

	private void setThisActivity(Activity activity) {
		modelActivity.setThisActivity(activity);

	}

	private Activity getActivity(Project project, String name) {
		return modelActivity.getActivity(project, name);
	}

	private void setHasActivity(boolean b) {
		modelActivity.setHasActivity(b);
	}

	private void changeStage(String stage) {
		modelApplication.changeStage(stage);
	}

	private Project getThisProject() {
		return modelProject.getThisProject();
	}

	private boolean activityExists(Project project, String name) {
		return modelActivity.activityExists(project, name);
	}

	private boolean hasActivity() {
		return modelActivity.hasActiviy();
	}

	private String getCommand() {
		return scanner.next();
	}

	public void addProject(Project project) throws OperationNotAllowedException {
		modelProject.addProject(project);
	}

	public boolean hasProject(String string) {
		return modelProject.hasID(string);
	}

	public boolean addActivity(String string, Project project) throws OperationNotAllowedException {
		return modelActivity.addActivity(project, string);
	}

	public boolean validDate(String startDate) {
		return modelActivity.verifyDateFormat(startDate);
	}

	public void setActivityStart(Project project, Activity activity, String startDate)
			throws OperationNotAllowedException {
		modelActivity.setActivityStart(project, activity, startDate);
	}

	public void setActivityEnd(Project project, Activity activity, String endDate) throws OperationNotAllowedException {
		modelActivity.setActivityEnd(project, activity, endDate);

	}
}

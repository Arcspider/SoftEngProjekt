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
	private ApplicationManager applicationManager;
	private ProjectManager projectManager;
	private ActivityManager activityManager;
	private WorkerManager workerManager;
	private View view;
	Scanner scanner;
	Scanner timeHanlder;

	public ControllerActivity(View view, ApplicationManager applicationManager, ProjectManager projectManager,
			ActivityManager activityManager, WorkerManager workerManager) {
		this.view = view;
		this.applicationManager = applicationManager;
		this.projectManager = projectManager;
		this.activityManager = activityManager;
		this.workerManager = workerManager;
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
			view.showAvailableCommands(applicationManager.getStage());
			String nextCommand = getCommand();
			switch (nextCommand) {
			case "Date":
				view.showMessage("Type \"Start\" to change the start date of the project");
				view.showMessage("Type \"End\" to change the end date of the project");
				view.showMessage("Type \"Budget\"to set the total hours on a budget");
				view.showMessage("Type \"Getbudget\"to get information regarding the budgetted hours on the activity");
				view.showMessage("The date format is \"ww-yyyy\" where ww is week and yyyy is year");
				
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
					getThisActivity().setBudgettedHours(timeHanlder.nextLine());
					break;
				case "Getbudget":
					view.showMessage("There is a total of " + getThisActivity().getBudgettedHoursTotal() + " Allocated on the budget, and has " + getThisActivity().getBudgettedHoursLeft()  + " hours left");	
				}
				break;
			case "Back":
				setHasActivity(false);
				changeStage("Project");

				break;
			case "Assign":
				view.showMessage("Please enter the ID of the employee you want to assign to this activity");
				nextCommand = getCommand();
				if (workerManager.workerHasID(nextCommand)) {
					getThisActivity().assignWorker(workerManager.getWorker(nextCommand));
				}
				break;
			case "Shift":
				view.showMessage("Please supply the following information");
				view.showMessage("WorkerID: ");
				String workerID = getCommand();
				view.showMessage("Date (in the format: dd-mm-yyyy): ");
				String date = getCommand();
				view.showMessage("Time: ");
				String time = getCommand();
				activityManager.addShift(getThisActivity(), workerID, date, time);
				if(!workerManager.getWorker(workerID).getAbsence()) {
					getThisActivity().addShift(workerID, date, time);
				} else {
					view.showMessage("This employee is absent, so they cannot get new shifts");
					view.showMessage("");
				}
				break;
			case "List":
				getThisActivity().listWorkers();
				break;
			case "Check":
				getThisActivity().getBudgettedHoursLeft();
				break;
			
			case "Remove":
				view.showMessage("Please enter the id of the employee");
				String workerId = getCommand();
				view.showMessage("Please enter the date of the shift");
				date = getCommand();
				Activity currentActivity = getThisActivity();
				if(currentActivity.removeShift(workerId,currentActivity.stringToDate(date))) {
					view.showMessage("The shift was removed");
				}else {
					view.showMessage("Shift not found");
				}
				break;
			case "View":
				getThisActivity().printShifts();
				break;		
			}
		}
	}

	private Activity getThisActivity() {
		return activityManager.getThisActivity();
	}

	private void setThisActivity(Activity activity) {
		activityManager.setThisActivity(activity);

	}

	private Activity getActivity(Project project, String name) {
		return activityManager.getActivity(project, name);
	}

	private void setHasActivity(boolean b) {
		activityManager.setHasActivity(b);
	}

	private void changeStage(String stage) {
		applicationManager.changeStage(stage);
	}

	private Project getThisProject() {
		return projectManager.getThisProject();
	}

	private boolean activityExists(Project project, String name) {
		return activityManager.activityExists(project, name);
	}

	private boolean hasActivity() {
		return activityManager.hasActiviy();
	}

	private String getCommand() {
		return scanner.next();
	}

	public void addProject(Project project) throws OperationNotAllowedException {
		projectManager.addProject(project);
	}

	public boolean hasProject(String string) {
		return projectManager.hasID(string);
	}

	public boolean addActivity(String string, Project project) throws OperationNotAllowedException {
		return activityManager.addActivity(project, string);
	}

	public boolean validDate(String startDate) {
		return activityManager.verifyDateFormat(startDate);
	}

	public void setActivityStart(Project project, Activity activity, String startDate)
			throws OperationNotAllowedException {
		activityManager.setActivityStart(project, activity, startDate);
	}

	public void setActivityEnd(Project project, Activity activity, String endDate) throws OperationNotAllowedException {
		activityManager.setActivityEnd(project, activity, endDate);

	}
}

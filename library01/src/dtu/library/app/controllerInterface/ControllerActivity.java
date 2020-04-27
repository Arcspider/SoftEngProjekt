package dtu.library.app.controllerInterface;

import java.util.Scanner;

import dtu.library.app.Activity;
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

	public void runCommand() throws OperationNotAllowedException {
		if(! hasActivity()) {
			String name = getCommand();
			if(!activityExists(getThisProject(),name)) {
				changeStage("Project");
				view.showMessage("This project " + getThisProject().getId()+" has the activity " + name );
			}else {
				setHasActivity(true);
				setThisActivity(getActivity(getThisProject(), name));
			}
		}
	}
	private void setThisActivity(Activity activity) {
		model.setThisActivity(activity);
		
	}

	public Activity getActivity(Project project, String name) {
		return model.getActivity(project,name);
	}

	private void setHasActivity(boolean b) {
		model.setHasActivity(b);
		
	}

	public void changeStage(String stage) {
		model.changeStage(stage);
	}

	private Project getThisProject() {
		return model.getThisProject();
	}

	public boolean activityExists(Project project, String name) {
		return model.activityExists(project, name);
	}

	private boolean hasActivity() {
		return model.hasActiviy();
	}

	private String getCommand() {
		return scanner.next();
	}

	public void addProject(Project project) throws OperationNotAllowedException {
		model.addProject(project);
	}

	public boolean hasProject(String string) {
		return model.hasID(string);
	}

	public boolean addActivity(String string, Project project) throws OperationNotAllowedException {
		 return model.addActivity(project, string);
	}

	public boolean validDate(String startDate) {
		return model.verifyDateFormat(startDate);
	}

	public void setActivityStart(Project project, Activity activity, String startDate) throws OperationNotAllowedException {
		model.setActivityStart(project,activity, startDate);
	}

	public void setActivityEnd(Project project, Activity activity, String endDate) throws OperationNotAllowedException {
		 model.setActivityEnd(project,activity,endDate);
		
	}
}

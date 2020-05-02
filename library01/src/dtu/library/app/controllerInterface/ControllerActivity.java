package dtu.library.app.controllerInterface;

import java.util.Scanner;

import dtu.library.app.Activity;
import dtu.library.app.Model;
import dtu.library.app.ModelActivity;
import dtu.library.app.ModelAplication;
import dtu.library.app.ModelProject;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

public class ControllerActivity {
	private Model model;
	private ModelAplication modelAplication;
	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private View view;
	Scanner scanner;
	Scanner timeHanlder;

	public ControllerActivity(View view, Model model,ModelAplication modelAplication, ModelProject modelProject, ModelActivity modelActivity) {
		this.view = view;
		this.model = model;
		this.modelAplication = modelAplication;
		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		scanner = new Scanner(System.in);
		timeHanlder = new Scanner(System.in);
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
		}else{
			String nextCommand = getCommand();
			if (nextCommand.equals("Time")) {
				System.out.println("time");
				nextCommand = getCommand();
				if (nextCommand.equals("Start")) {
					setActivityStart(getThisProject(), getThisActivity(), timeHanlder.nextLine());

				} else if (nextCommand.equals("End")) {
					setActivityEnd(getThisProject(), getThisActivity(), timeHanlder.nextLine());
				}
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
		return modelActivity.getActivity(project,name);
	}

	private void setHasActivity(boolean b) {
		modelActivity.setHasActivity(b);
	}

	private void changeStage(String stage) {
		modelAplication.changeStage(stage);
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

	public void setActivityStart(Project project, Activity activity, String startDate) throws OperationNotAllowedException {
		modelActivity.setActivityStart(project,activity, startDate);
	}

	public void setActivityEnd(Project project, Activity activity, String endDate) throws OperationNotAllowedException {
		 modelActivity.setActivityEnd(project,activity,endDate);
		
	}
}

package dtu.library.app.controllerInterface;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerProject {
	private Model model;
	private View view;

	Scanner scanner;
	Scanner descriptionHandler;

	public ControllerProject(View view, Model model) {
		this.view = view;
		this.model = model;
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
				System.out.println("Project does not exist. Commands: Create, Get");
			} else {
				setHasProject(true);
				setThisProject(id);
				System.out.println(getThisProject().toString());
				System.out.println();
				System.out.println("Current available commands: Name, Description, Activity, Remove");
				System.out.println("Name: Change the name of the project");
				System.out.println("Description: Change the description of the project");
				System.out.println("Remove: Remove this project. WARNING: Once removed, the project is permanently inaccessible");
			}
		}else if (getHasProject()) {
			String nextCommand = getCommand();
			if (nextCommand.equals("Description")) {
				System.out.println("Please enter a desired description");
				editProjectDescription(getThisProject(), getDescription());
				
			}else if (nextCommand.equals("Name")) {
				System.out.println("Please enter the new name");
				editProjectName(getThisProject(), getCommand());
				
			}else if(nextCommand.equals("Remove")) {
				removeProject(getThisProject());
				setHasProject(false);
				System.out.println("The project has been removed. Available commands: Create, Get");
				model.changeStage("Application");
			}else if (nextCommand.equals("Add")) {
				addActivity(getThisProject(), getCommand());
			}else if(nextCommand.equals("Edit")) {
				changeStage("Activity");
			}
		}
	}

	private void addActivity(Project project, String name) throws OperationNotAllowedException {
		model.addActivity(project,name);	
		
	}

	private boolean getHasProject() {
		return model.getHasProject();
	}

	private Project getThisProject() {
		return model.getThisProject();
	}

	private void setThisProject(String id) {
		model.setThisProject(id);
		
	}

	private void setHasProject(boolean is) {
		model.setHasProject(is);
		
	}

	private void changeStage(String stage) {
		model.changeStage(stage);
		
	}

	private String getDescription() {
		return descriptionHandler.nextLine();
	}

	public void removeProject(Project project) throws OperationNotAllowedException {
		model.removeProject(project);

	}

	public boolean exists(String ID) {
		return model.hasID(ID);
	}

	public boolean editProjectDescription(Project project, String description) {
		return model.editProjectDescription(project, description);
	}

	public boolean editProjectName(Project project, String name) {
		return model.editProjectName(project, name);

    }
    public ArrayList<Project> getProjects(){
        return model.getProjects();
    }


    public void setProjectTime(Project project, String startDate, String endDate){
		model.setProjectDates(project, startDate, endDate);
	}
	public LocalDate getProjectStart(Project project){
		return model.getProjectStart(project);
	}
	public LocalDate getProjectEnd(Project project){
		return model.getProjectEnd(project);
	}

	public boolean validDate(String someDate) {
		return model.verifyDateFormat(someDate);
	}
}

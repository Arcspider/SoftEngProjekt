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

	public ControllerProject(View view, Model model) {
		this.view = view;
		this.model = model;
		scanner = new Scanner(System.in);
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
				System.out.println("Project does not exist. Commands: Get or Create");
			} else {
				setHasProject(true);
				setThisProject(id);
				System.out.println(getThisProject().toString());
			}
		}else if (getHasProject()) {
			System.out.println("project commands is Description, Name or Remove");
			String nextCommand = getCommand();
			if (nextCommand.equals("Description")) {
				editProjectDescription(getThisProject(), getDescription());
			}else if (nextCommand.equals("Name")) {
				editProjectName(getThisProject(), getCommand());
			}else if(nextCommand.equals("Remove")) {
				removeProject(getThisProject());
				setHasProject(false);
				model.changeStage("Application");
			}else if(nextCommand.equals("Activity")) {
				changeStage("Activity");
			}
		}
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
		return scanner.nextLine();
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

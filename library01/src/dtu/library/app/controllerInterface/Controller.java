package dtu.library.app.controllerInterface;

import java.util.*;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

public class Controller {
	private Model model;
<<<<<<< Updated upstream:library01/src/dtu/library/app/controllerInterface/Controller.java
=======
	private ModelApplication modelAplication;
	private ModelProject modelProject;
>>>>>>> Stashed changes:library01/src/dtu/library/app/Controller.java
	private View view;
	private Project newProject;
	Scanner scanner;

<<<<<<< Updated upstream:library01/src/dtu/library/app/controllerInterface/Controller.java
	public Controller(View view, Model model) {
=======
	public Controller(View view, Model model, ModelApplication modelAplication, ModelProject modelProject ) {
>>>>>>> Stashed changes:library01/src/dtu/library/app/Controller.java
		this.view = view;
		this.model = model;
		scanner = new Scanner(System.in);
	}

	public String getCommand() {
		return scanner.next();
	}

	public void runCommand(String nextCommand) throws OperationNotAllowedException {
		if (nextCommand.equals("Create")) {
			System.out.println("Name the Project: ");
			String name = getCommand();
			newProject = createProject(name);
			  if(checkName(name)) {
			    	addProject(newProject);
			  }
		} else if (nextCommand.equals("Get")) {
			model.changeStage("Project");
			
		} else if (nextCommand.equals("Exit")) {
			System.exit(0);
			
		} else if (nextCommand.equals("Worker")) {
			model.changeStage("Worker");
			view.showAvailableCommands(model.getStage());
		}
		
	}


	public Project createProject(String name) throws OperationNotAllowedException {
		return model.createProject(name);
	}

	public boolean hasID(String id) {
		return model.hasID(id);
	}

	public boolean checkName(String name) throws OperationNotAllowedException {
		return model.checkName(name);
	}

	public void addProject(Project project) throws OperationNotAllowedException {
		model.addProject(project);	
	}

}

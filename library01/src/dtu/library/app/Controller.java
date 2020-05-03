package dtu.library.app;

import java.util.*;

public class Controller {
	private Model model;
	private ModelAplication modelAplication;
	private ModelProject modelProject;
	private View view;
	private Project newProject;
	Scanner scanner;

	public Controller(View view, Model model, ModelAplication modelAplication, ModelProject modelProject ) {
		this.view = view;
		this.model = model;
		this.modelAplication = modelAplication;
		this.modelProject = modelProject;
		scanner = new Scanner(System.in);
	}

	String getCommand() {
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
			modelAplication.changeStage("Project");
			
		} else if (nextCommand.equals("Exit")) {
			System.exit(0);
			
		} else if (nextCommand.equals("Worker")) {
			modelAplication.changeStage("Worker");
			view.showAvailableCommands(modelAplication.getStage());
		}
		
	}


	public Project createProject(String name) throws OperationNotAllowedException {
		return modelProject.createProject(name);
	}

	public boolean hasID(String id) {
		return modelProject.hasID(id);
	}

	public boolean checkName(String name) throws OperationNotAllowedException {
		return modelProject.checkName(name);
	}

	public void addProject(Project project) throws OperationNotAllowedException {
		modelProject.addProject(project);	
	}

}

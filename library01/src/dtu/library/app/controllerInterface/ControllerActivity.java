package dtu.library.app.controllerInterface;

import java.util.Scanner;

import dtu.library.app.Model;
import dtu.library.app.Project;
import dtu.library.app.View;

public class ControllerActivity {
	    private View view;
	    private Model model;
	    Scanner scanner;

	    public ControllerActivity(View view,  Model model) {
	        this.view = view;
	        this.model = model;
	        scanner = new Scanner(System.in);
	    }

	    int getCommand() {
	        return scanner.nextInt();
	    } 
	    public void runCommand(String nextCommand, String name) {
	    	if(nextCommand.contains("Add Activity")) {
	    		
	    	}
	    }

		public boolean exists(String id) {
			return model.hasID(id);
		}

		public void addProject(Project project) {
			model.addProject(project);
		}


		public boolean addActivity(Project project, String string) {
			return model.addActivity(project,string);
			
		}

		public boolean hasActivity(String stringOne, String stringTwo) {
			return model.hasActivity(stringOne,stringTwo);
		}
}

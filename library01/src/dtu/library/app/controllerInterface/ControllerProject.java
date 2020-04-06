package dtu.library.app.controllerInterface;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

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

    int getCommand() {
        return scanner.nextInt();
    }

    public void runCommand(String nextCommand, String name) {
        if (nextCommand.equals("Create Project")) {
            model.createProject(name);
        } else if (nextCommand.equals("Get Project")) {
            view.showMessage("Please enter the id of the project");
            String userInput = getInput();
            System.out.println(model.getProject(userInput).toString());
        } else {
            System.out.print("No valido");
        }
    }

    public void addProject(Project project) {
        model.addProject(project);
    }

    public boolean exists(String ID) {
        return model.hasID(ID);
    }

    public Project getProject() {
        return model.getNewProject();
    }

    public String getInput() {
        return scanner.next();
    }

    public boolean checkName(String name) throws OperationNotAllowedException {
           return model.checkName(name);
    }
}

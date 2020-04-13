package dtu.library.app.controllerInterface;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
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

    int getCommand() {
        return scanner.nextInt();
    }

    public void runCommand(String nextCommand, String name) throws OperationNotAllowedException {
        if (nextCommand.equals("Create Project")) {
            String id = model.generateID();
            Project addedProject = model.createProject(name,id);
            model.addProject(addedProject);
        } else if (nextCommand.equals("Get Project")) {
            view.showMessage("Please enter the id of the project");
            String userInput = getInput();
            System.out.println(model.getProject(userInput).toString());
        } else {
            System.out.print("No valido"); 
        }
    }

    public Project createProject(String name, String id) {
        return model.createProject(name, id);
    }
    public String generateID() {
        return model.generateID();
    }
    public void addProject(Project project) throws OperationNotAllowedException {

        model.addProject(project);
    }

    public void removeProject(Project project) throws OperationNotAllowedException {
        model.removeProject(project);

    }

    public boolean exists(String ID) {
        return model.hasID(ID);
    }

    public Project getProject() {
        return model.getNewProject();
    }

    public Project getOldProject(String ID) throws OperationNotAllowedException {
        return model.getProject(ID);
    }

    public String getInput() {
        return scanner.next();
    }

    public boolean checkName(String name) throws OperationNotAllowedException {
        return model.checkName(name);
    }

    public boolean editProjectDescription(String ID, String description) throws OperationNotAllowedException {
        return model.editProjectDescription(ID,description);
    }
    public ArrayList<Project> getProjects(){
        return model.getProjects();
    }

    public boolean editProjectName(String ID, String name) {
        return model.editProjectName(ID,name);

    }
}


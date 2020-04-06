package dtu.library.app;

import java.text.*;
import java.util.*;

public class Model {
    private View view;
    private ArrayList<Project> projects;
    private Calendar calendar;
    private DateFormat dateFormat;
    private Random random;
    private Project newProject;

    public Model(View view) {
        this.view = view;
        projects = new ArrayList<Project>();
        this.calendar = new GregorianCalendar();
        dateFormat = new SimpleDateFormat("MM-yy");
        random = new Random();
    }

    public void createProject(String name) {
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String id = date + "-" + random.nextInt(100);
        newProject = new Project(name, id);
        view.showMessage("Project has been created with ID: " + id);

    }

    public boolean hasID(String ID) {
        for (Project project : projects) {
            if (project.getId().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public Project getNewProject() {
        return newProject.getProject();
    }

    public Project getProject(String id) {
        for (Project currentProject : projects) {
            String currentId = currentProject.getId();
            if (currentId.equals(id)) {
                return currentProject;
            }

        }
        return null;
    }

    public boolean containsProjectWithID(String ID) {
        return !(projects.contains(ID));
    }

    public boolean canBeCreated(String id) {
        return !projects.contains(id);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public boolean checkName(String name) throws OperationNotAllowedException {
        if (!name.equals("")) {
            return true;
        } else {
            throw new OperationNotAllowedException("The project has no name, so it was not created");
        }
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }
}

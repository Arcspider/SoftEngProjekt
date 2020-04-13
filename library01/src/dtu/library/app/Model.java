package dtu.library.app;

import java.text.*;
import java.time.LocalDate;
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

    public Project createProject(String name,String id) {
        newProject = new Project(name, id);
        view.showMessage("Project " + name  + " has been created with ID: " + id);
        return newProject;
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
    public String generateID() {
    	String date = dateFormat.format(Calendar.getInstance().getTime());
        String id = date + "-" + random.nextInt(100);
        while(hasID(id)) id = date + "-" + random.nextInt(100); 
    	return id;
    }
    public boolean containsProjectWithID(String ID) {
        return !(projects.contains(ID));
    }

	public boolean canBeCreated(String id) {
		return !projects.contains(id);
	}

    public void addProject(Project project) throws OperationNotAllowedException {
    	if(checkName(project.getName())) {    		
    		projects.add(project);
    	}
    }

    public boolean checkName(String name) throws OperationNotAllowedException {
        if (!name.equals("")) {
            return true;
        } else {
            throw new OperationNotAllowedException("The project has no name, so it was not created");
        }
    }
    public ArrayList<Project> getProjects(){
    	return projects;
    }
    public boolean editProjectDescription(String ID,String newDescription) {
    	Project projectToBeEdited = getProject(ID);
    	projectToBeEdited.setDescription(newDescription);
    	
    	return true;
    }

	public boolean editProjectName(String ID, String name) {
		Project projectToBeEdited = getProject(ID);
    	projectToBeEdited.setName(name);
    	
    	return true;
	}
	public void removeProject(Project project) throws OperationNotAllowedException {
		if (hasID(project.getId())) {
			projects.remove(project);
		} else {
			throw new OperationNotAllowedException("This project doesn't exist");
		}
	}
	public void setProjectDates(String startDate, String endDate) {
		String[] startWeekArray = startDate.split(" ");
		String[] endWeekArray = endDate.split(" ");
		int startWeekInt = Integer.parseInt(startWeekArray[1]);
		int startYearInt = Integer.parseInt(startWeekArray[3]);
		int endWeekInt = Integer.parseInt(endWeekArray[1]);
		int endYearInt = Integer.parseInt(endWeekArray[3]);

		Calendar cldStart = Calendar.getInstance();
		cldStart.set(Calendar.YEAR, startYearInt);
		cldStart.set(Calendar.WEEK_OF_YEAR, startWeekInt);
//		cldStart.set(Calendar.MONTH,0);
		cldStart.set(Calendar.DAY_OF_WEEK, 2);

		Calendar cldEnd = Calendar.getInstance();
		cldEnd.set(Calendar.YEAR, endYearInt);
		cldEnd.set(Calendar.WEEK_OF_YEAR, endWeekInt);
		cldEnd.set(Calendar.DAY_OF_WEEK, 6);

		Date resultStart = cldStart.getTime();
		Date resultEnd = cldEnd.getTime();
		System.out.println("Start date: " + resultStart);
		System.out.println("Start Month: " + cldStart.get(Calendar.MONTH));
		System.out.println("Start Day: " + cldStart.get(Calendar.DATE));

		System.out.println("End date: " + resultEnd);
		System.out.println("End Month: " + cldEnd.get(Calendar.MONTH));
		System.out.println("End Day: " + cldEnd.get(Calendar.DATE));
//		LocalDate startDate = LocalDate.of(startYearInt,);

//	public boolean addActivity(String string, Project project) throws OperationNotAllowedException {
//		return project.addActivity(string);
//	}
//
//	public boolean hasActivity(String sA, String sP) {
//		return getProject(sP).hasActivity(sA);
//	}
	}
}

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
	private Project thisProject;
	private boolean hasProject;
	private String stage;

	public Model(View view) {
		this.view = view;
		projects = new ArrayList<Project>();
		this.calendar = new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-yy");
		random = new Random();
		stage = "Application";
		hasProject = false;
	}

    public Project createProject(String name,String id) {
        newProject = new Project(name, id);
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
		while (hasID(id))
			id = date + "-" + random.nextInt(100);
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
    		view.showMessage("Project " + project.getName()  + " has been created with ID: " + project.getId());
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

	public boolean editProjectDescription(Project project, String newDescription) {
		project.setDescription(newDescription);
		return true;
	}

	public boolean editProjectName(Project project, String name) {
		project.setName(name);
		return true;
	}

	public void removeProject(Project project) throws OperationNotAllowedException {
		if (hasID(project.getId())) {
			projects.remove(project);
		} else {
			throw new OperationNotAllowedException("This project doesn't exist");
		}
	}

	public void setProjectDates(Project project, String startDate, String endDate) {
		if(verifyDateFormat(startDate) && verifyDateFormat(endDate)) {
			
			LocalDate startProjectDate = stringToDate(startDate);
			LocalDate endProjectDate = stringToDate(endDate);
			if(endProjectDate.isAfter(startProjectDate)) {			
				project.setStartDate(startProjectDate);
				project.setEndDate(endProjectDate);
				
				System.out.println("LocalDate start: " + startProjectDate);
				System.out.println("LocalDate end: " + endProjectDate);
			} else {
				System.out.println("The indicated start of the project is before the indicated end.");
			}
		}else {
			System.out.println("The date format was invalid.");
		}
	}
	
	public LocalDate stringToDate(String toBeConverted) {
		String[] stringDate = toBeConverted.split(" ");
		int weekInt = Integer.parseInt(stringDate[1]);
		int yearInt = Integer.parseInt(stringDate[3]);

		Calendar cldStart = Calendar.getInstance();
		cldStart.set(Calendar.YEAR, yearInt);
		cldStart.set(Calendar.WEEK_OF_YEAR, weekInt);
		LocalDate finalDate = LocalDate.of(yearInt,cldStart.get(Calendar.MONTH)+1,cldStart.get(Calendar.DATE));
		return finalDate;
		
	}


//	public boolean addActivity(String string, Project project) throws OperationNotAllowedException {
//		return project.addActivity(string);
//	}
//
//	public boolean hasActivity(String sA, String sP) {
//		return getProject(sP).hasActivity(sA);
//	}
	//Kan ikke verificere for forskellige mdr. Eksempelvis tror den at alle m�neder har 31 dage
	public boolean verifyDateFormat(String dateToVerify) {
		String[] stringDate = dateToVerify.split(" ");
		int weekInt = Integer.parseInt(stringDate[1]);
		int yearInt = Integer.parseInt(stringDate[3]);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		//�rstallene man arbejder indenfor er 50 �r
		if(yearInt+50 >= currentYear || yearInt-50 <= currentYear  ) {
			if(weekInt > 0 && weekInt <= 52) {
						return true;
			}
		}
		
		return false;
	}

	public LocalDate getProjectStart(Project project) {
		return project.getStartDate();
	}

	public LocalDate getProjectEnd(Project project) {
		return project.getEndDate();
	}

	public Project createProject(String name) throws OperationNotAllowedException {
		String id = generateID();
		newProject = new Project(name, id);
	    view.showMessage("Project " + name  + " has been created with ID: " + id);
	  
	    return newProject;
	}

	public void changeStage(String stage) {
		this.stage = stage; 
	}

	public String getStage() {
		return stage;
	}

	public void setHasProject(boolean is) {
		hasProject = is;
	}

	public boolean getHasProject() {
		return hasProject;
	}

	public void setThisProject(String id) {
		thisProject = getProject(id);
	}

	public Project getThisProject() {
		return thisProject;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}

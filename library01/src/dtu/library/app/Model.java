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
		if (checkName(project.getName())) {
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

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public boolean editProjectDescription(Project project, String newDescription) {
		project.setDescription(newDescription);
		System.out.println(project.toString());
		return true;
	}

	public boolean editProjectName(Project project, String name) {
		project.setName(name);
		System.out.println(project.toString());
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
		String[] startWeekArray = startDate.split(" ");
		String[] endWeekArray = endDate.split(" ");
		int startWeekInt = Integer.parseInt(startWeekArray[1]);
		int startYearInt = Integer.parseInt(startWeekArray[3]);
		int endWeekInt = Integer.parseInt(endWeekArray[1]);
		int endYearInt = Integer.parseInt(endWeekArray[3]);

		Calendar cldStart = Calendar.getInstance();
		cldStart.set(Calendar.YEAR, startYearInt);
		cldStart.set(Calendar.WEEK_OF_YEAR, startWeekInt);

		Calendar cldEnd = Calendar.getInstance();
		cldEnd.set(Calendar.YEAR, endYearInt);
		cldEnd.set(Calendar.WEEK_OF_YEAR, endWeekInt);
		cldEnd.set(Calendar.DAY_OF_WEEK, 6);

		LocalDate startProjectDate = LocalDate.of(startYearInt,cldStart.get(Calendar.MONTH)+1,cldStart.get(Calendar.DATE));
		LocalDate endProjectDate = LocalDate.of(endYearInt,cldEnd.get(Calendar.MONTH)+1,cldEnd.get(Calendar.DATE));

		project.setStartDate(startProjectDate);
		project.setEndDate(endProjectDate);

		System.out.println("LocalDate start: " + startProjectDate);
		System.out.println("LocalDate end: " + endProjectDate);

//	public boolean addActivity(String string, Project project) throws OperationNotAllowedException {
//		return project.addActivity(string);
//	}
//
//	public boolean hasActivity(String sA, String sP) {
//		return getProject(sP).hasActivity(sA);
//	}
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
		this.stage = state;
	}
}

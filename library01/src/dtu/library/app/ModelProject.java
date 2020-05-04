package dtu.library.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class ModelProject {
	private View view;
	private ArrayList<Project> projects;
	private Project newProject;
	private Project thisProject;
	private boolean hasProject;
	private Random random;
	private DateFormat dateFormat;
	public ModelProject(View view) {
		this.view = view;
		projects = new ArrayList<Project>();
		random = new Random();
		hasProject = false;
		new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-yy");
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

	public String projectGenerateID() {
		String date = dateFormat.format(Calendar.getInstance().getTime());
		String id = date + "-" + random.nextInt(100);
		while (hasID(id))
			id = date + "-" + random.nextInt(100);
		return id;
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

	public void setProjectStart(Project project, String startDate) {
		if (verifyDateFormat(startDate)) {
			LocalDate startProjectDate = stringToDate(startDate);
			project.setStartDate(startProjectDate);
			System.out.println("The project starts: " + startProjectDate);
		}
	}

	public void setProjectEnd(Project project, String endDate) {
		if (verifyDateFormat(endDate)) {
			LocalDate startProjectDate = project.getStartDate();
			LocalDate endProjectDate = stringToDate(endDate);
			if (startProjectDate == null || endProjectDate.isAfter(startProjectDate)) {
				project.setEndDate(endProjectDate);
				System.out.println("The project ends: " + endProjectDate);
			} else {
				System.out.println("Date wasn't set, as it was invalid.");
			}

		}
	} 

	public LocalDate stringToDate(String toBeConverted) {
		String[] stringDate = toBeConverted.split("-");
		int weekInt = Integer.parseInt(stringDate[0]);
		int yearInt = Integer.parseInt(stringDate[1]);

		Calendar cldStart = Calendar.getInstance();
		cldStart.set(Calendar.YEAR, yearInt);
		cldStart.set(Calendar.WEEK_OF_YEAR, weekInt);
		return LocalDate.of(yearInt, cldStart.get(Calendar.MONTH) + 1, cldStart.get(Calendar.DATE));
	}

	public boolean verifyDateFormat(String dateToVerify) {

		String[] stringDate = dateToVerify.split("-");
		if (stringDate.length == 2 && stringIsNumeric(stringDate[0]) && stringIsNumeric(stringDate[1])) {

			int weekInt = Integer.parseInt(stringDate[0]);
			int yearInt = Integer.parseInt(stringDate[1]);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int difference = yearInt - currentYear;
			// �rstallene man arbejder indenfor er 50 �r
			if (difference >= -50 && difference <= 50) {
				return weekInt > 0 && weekInt <= 52;
			}
		}
		return false;
	}

	public boolean stringIsNumeric(String test) {
		try {
			int pls = Integer.parseInt(test);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public LocalDate getProjectStart(Project project) {
		return project.getStartDate();
	}

	public LocalDate getProjectEnd(Project project) {
		return project.getEndDate();
	}

	public Project createProject(String name) throws OperationNotAllowedException {
		String id = projectGenerateID();
		newProject = new Project(name, id);
		view.showMessage("Project " + name + " has been created with ID: " + id);

		return newProject;
	}

	public boolean getHasProject() {
		return hasProject;
	}

	public void setHasProject(boolean is) {
		hasProject = is;
	}

	public Project getThisProject() {
		return thisProject;
	}

	public void setThisProject(String id) {
		thisProject = getProject(id);
	}

	public boolean setLeader(Project project, Worker worker) throws OperationNotAllowedException {
		if (!project.hasLeader()) {
			project.setLeader(worker);
			view.showMessage(worker.getId() + " is now the leader for this project");
			return true;
		} else if (project.hasLeader() && project.getLeader().equals(worker)) {
			throw new OperationNotAllowedException("This worker is already leader for the project");	
		}else {
			throw new OperationNotAllowedException("This project already has a leader");
		}
	}

	public boolean hasLeader(Project project) {
		return project.hasLeader();
	}
}

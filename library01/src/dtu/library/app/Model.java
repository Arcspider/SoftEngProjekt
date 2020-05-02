package dtu.library.app;

import java.text.*;
import java.time.*;
import java.util.*;

public class Model {
	private View view;
	private ArrayList<Project> projects;
	private ArrayList<Worker> workers;
	private Calendar calendar;
	private DateFormat dateFormat;
	private Random random;
	private Project newProject;
	private Project thisProject;
	private Worker worker;
	private Activity thisActivity;
	private boolean hasProject;
	private boolean hasActivity;
	private String stage;

	public Model(View view) {
		this.view = view;
		projects = new ArrayList<Project>();
		workers = new  ArrayList<Worker>();
		this.calendar = new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-yy");
		random = new Random();
		stage = "Application";
		//TODO �ndre variabel navnene til noget fornuftigt, s� det ikke er i konflikt med om et project har en aktivitet osv.
		hasProject = false;
		hasActivity = false;
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
		if(verifyDateFormat(startDate)) {
			LocalDate startProjectDate = stringToDate(startDate);
			project.setStartDate(startProjectDate);
			System.out.println("LocalDate start: " + startProjectDate);
		}
	}

	public void setProjectEnd(Project project, String endDate) {
		if (verifyDateFormat(endDate)) {
			LocalDate startProjectDate = project.getStartDate();
			LocalDate endProjectDate = stringToDate(endDate);
			if (startProjectDate == null || endProjectDate.isAfter(startProjectDate)) {
				project.setEndDate(endProjectDate);
				System.out.println("LocalDate end: " + endProjectDate);
			} else {
				System.out.println("Date wasn't set, as it was invalid.");
			}

		}
	}

	public void setActivityStart(Project project,Activity currentActivity, String startDate) throws OperationNotAllowedException {
		System.out.println("This is the current activity " + currentActivity);
		LocalDate startActivityDate = project.getStartDate();
		LocalDate endActivityDate = project.getEndDate();
		if (verifyDateFormat(startDate)) {
			if(endActivityDate == null || startActivityDate.isBefore(endActivityDate)) {
				LocalDate newStart = stringToDate(startDate);
				project.setActivityStartDate(currentActivity,newStart);
			}else throw new OperationNotAllowedException("End date is before start date");
		}
	}
	public void setActivityEnd(Project project,Activity currentActivity, String endDate) throws OperationNotAllowedException {
		LocalDate startActivityDate = project.getStartDate();
		LocalDate endActivityDate = project.getEndDate();
		if (verifyDateFormat(endDate)) {
			if(endActivityDate == null || startActivityDate.isBefore(endActivityDate)) {
				LocalDate newEnd = stringToDate(endDate);
				project.setActivityEndDate(currentActivity,newEnd);
			}else throw new OperationNotAllowedException("Start date is after end date");
		}
	}


	public LocalDate stringToDate(String toBeConverted) {
		String[] stringDate = toBeConverted.split("-");
		int weekInt = Integer.parseInt(stringDate[0]);
		int yearInt = Integer.parseInt(stringDate[1]);

		Calendar cldStart = Calendar.getInstance();
		cldStart.set(Calendar.YEAR, yearInt);
		cldStart.set(Calendar.WEEK_OF_YEAR, weekInt);
		LocalDate finalDate = LocalDate.of(yearInt, cldStart.get(Calendar.MONTH) + 1, cldStart.get(Calendar.DATE));
		return finalDate;

	}

	public boolean verifyDateFormat(String dateToVerify) {

		String[] stringDate = dateToVerify.split("-");
		if(stringDate.length == 2 && stringIsNumeric(stringDate[0]) && stringIsNumeric(stringDate[1])) {

			int weekInt = Integer.parseInt(stringDate[0]);
			int yearInt = Integer.parseInt(stringDate[1]);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int difference = yearInt - currentYear;
			// �rstallene man arbejder indenfor er 50 �r
			if (difference >= -50 && difference <= 50) {
				if (weekInt > 0 && weekInt <= 52) {
					return true;
				}
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
		String id = projectGenerateID();
		newProject = new Project(name, id);
		view.showMessage("Project " + name + " has been created with ID: " + id);

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

	public boolean addActivity(Project project, String name) throws OperationNotAllowedException {
		return project.addActivity(name);
	}

	public boolean hasActiviy() {
		return hasActivity;
	}

	public boolean activityExists(Project project, String name) {
		return project.hasActivity(name);
	}

	public void setHasActivity(boolean b) {
		hasActivity = b;

	}

	public Activity getActivity(Project project, String name) {
		return project.getActivity(name);
	}

	public void setThisActivity(Activity activity) {
		thisActivity = activity;
	}

	public void setState(String state) {
		this.stage = state;
	}

	public Worker createWorker(String firstname, String lastname ) {
		String id = workerGenerateID(firstname, lastname);
		worker = new Worker(firstname ,lastname, id, this);
		addWorker(worker);
		return worker;
	}

	public void addWorker(Worker worker) {
		workers.add(worker);

	}

	private String workerGenerateID(String firstname, String lastname) {
		String id = ""+firstname.charAt(0)  + lastname.charAt(0) + random.nextInt(100);
		while (workerHasID(id))
			id = ""+firstname.charAt(0)  + lastname.charAt(0) + random.nextInt(100);
		return id;
	}

	public boolean workerHasID(String id) {
		for (Worker worker : workers) {
			if (worker.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public Worker getWorker(String id) {
		for (Worker worker : workers) {
			if (worker.getId().equals(id)) {
				return worker;
			}
		}
		return null;
	}


	public boolean verifyLegalActivityName(Project project,String activityName) {
		if(activityName == null || project.hasActivity(activityName) ) return false;
		else return true;
	}

	public Activity getThisActivity() {
		return thisActivity;
	}

	public void changeActivityName(Project project, Activity activity, String newActivityName) throws OperationNotAllowedException {
		if(verifyLegalActivityName(project, newActivityName)) {
			project.changeActivityName(activity,newActivityName);
		}else {
			throw new OperationNotAllowedException("Illegal name");
		}
	}

	public boolean legalDescription(String description) {
		if(description == "" || description == null)return false;
		else return true;

	}

	public void changeActivityDescription(Project project, Activity activity, String newDescription) throws OperationNotAllowedException {
		if(legalDescription(newDescription)) {
			project.changeActivityDescription(activity, newDescription);
		}
	}

	public boolean assignWorker(Activity activity, Worker worker) {
		activity.assignWorker(worker);
		worker.assignWorker(activity);
		return true;
	}
	public boolean stringIsNumeric(String test) {
		try {
			int pls = Integer.parseInt(test);
		} catch(NumberFormatException e){
			return false;
		}
		return true;
	}


}

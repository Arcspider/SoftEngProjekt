package dtu.library.app;

import java.util.ArrayList;

public class Project {
	private String description;
	private String name;
	private String id;
	private ArrayList<Activity> activities;

	public Project(String Name, String ID) {
		this.name = Name;
		this.id = ID;
		activities = new ArrayList<Activity>();
	}

	public Project getProject() {
		return this;
	}

	public String getId() {
		return this.id;
	}

	public String toString() {
		return "This project is named" + name + " with description" + description + " and id " + id;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;

	}

	public boolean addActivity(String string) throws OperationNotAllowedException {
		if (!hasActivity(string)) {
			activities.add(new Activity(string));
			System.out.println("Has added activity " + string + " to project " + name);
			return true;
		}
		throw new OperationNotAllowedException("The project already has an activity with the name " + string);
	}

	public boolean hasActivity(String sA) {
		for (Activity currentActivity : activities) {
			String currentId = currentActivity.getName();
			if (currentId.equals(sA)) {
				return true;
			}
		}
		return false;
	}
}

package dtu.library.app;

import java.util.ArrayList;

public class Project {
	private String description;
	private String name;
	private String id;
	private ArrayList<Activity> activities; 
	private Activity newActivity;
	
	public Project(String Name, String ID) {
		this.name = Name;
		this.id = ID;
		activities = new ArrayList<Activity>();
	}

	public Project getProject(){
		return this;
	}

	public String getId() {
		return this.id;
	}
	public String toString() {
		return "This project is named PLACEHOLDER with description PLACEHOLDER and id " + id;
	}

    public String getName() {
		return name;
	}
 
    public boolean addActivity(String name) {
		newActivity = new Activity(name);
		activities.add(newActivity);
		return true;
    }

	public boolean hasActivity(String string) {
		return activities.contains(string);
	}
    
}

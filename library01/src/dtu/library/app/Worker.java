package dtu.library.app;

import java.util.ArrayList;


public class Worker {
	private String firstname;
	private String lastname;
	private String id;
	private ArrayList<Activity> activities;
	
	public Worker(String firstname, String lastname , String ID) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = ID;
		activities = new ArrayList<Activity>(); 
	}

	public Worker getWorker(){
		return this;
	}

	public String getId() {
		return this.id;
	}
	public String toString() {
		return this.firstname + " " + this.firstname + "'s new ID is \"" + id + "\"";
	}

	public String getName() {
		return this.firstname + " " + this.lastname;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	public String getLastName() {
		return this.lastname;
	}

	public void setID(String id) {
		this.id = id;
		
	}

	public void assignWorker(Activity activity) {
		activities.add(activity);
		
	}

}

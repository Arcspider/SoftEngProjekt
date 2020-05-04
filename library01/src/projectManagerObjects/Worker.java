package projectManagerObjects;

import java.util.ArrayList;


public class Worker {
	private String firstName;
	private String lastName;
	private String id;
	private ArrayList<Activity> activities;
	
	public Worker(String firstname, String lastname , String ID) {
		this.firstName = firstname;
		this.lastName = lastname;
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
		return this.firstName + " " + this.lastName + "'s new ID is \"" + id + "\"";
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}

	public void setID(String id) {
		this.id = id;
		
	}

	public void assignWorker(Activity activity) {
		activities.add(activity);
		
	}

}

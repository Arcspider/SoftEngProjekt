package dtu.library.app;

import java.util.ArrayList;
import java.util.List;

public class Worker {
	private String firstname;
	private String lastname;
	private String id;
//	private List<userTime> userTimeList;
	private ArrayList<Activity> activities;

	public Worker(String firstname, String lastname , String id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		activities = new ArrayList<Activity>(); 
	}

	public Worker getWorker(){
		return this;
	}

	public String getId() {
		return this.id;
	}
	public String toString() {
		return "This WORKERS ID is " + id;
	}

	public void setID(String id) {
		this.id = id;
		
	}

	public void assignWorker(Activity activity) {
		activities.add(activity);
		
	}

}

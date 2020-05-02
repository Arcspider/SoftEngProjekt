package dtu.library.app;

import java.util.List;

public class Worker {
	private String firstname;
	private String lastname;
	private String id;
	private Model model;
//	private List<userTime> userTimeList;
	private List<Activity> activities;

	public Worker(String firstname, String lastname , String ID, Model model) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = ID;
		this.model = model;
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

	public void setID(String id) {
		this.id = id;
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
	
	public boolean exists() {
		if(model.workerHasID(this.getId())) {
			return true;
		}
		return false;
	}

}

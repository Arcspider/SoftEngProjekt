package dtu.library.app;

public class Worker {
	private String firstname;
	private String lastname;
	private String id;
//	private List<userTime> userTimeList;

	public Worker(String firstname, String lastname , String ID) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = ID;
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
	
//	public String getName() {
//		return name;
//	}

}

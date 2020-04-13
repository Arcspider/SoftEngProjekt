package dtu.library.app;

public class Worker {
	private String name;
	private String id;
//	private List<userTime> userTimeList;

	public Worker(String Name, String ID) {
		this.name = Name;
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

	public String getName() {
		return name;
	}

}

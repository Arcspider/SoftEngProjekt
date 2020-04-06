package dtu.library.app;

public class Project {
	private String description;
	private String name;
	private String id;
	
	public Project(String Name, String ID) {
		this.name = Name;
		this.id = ID;
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
}

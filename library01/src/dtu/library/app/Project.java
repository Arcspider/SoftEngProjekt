package dtu.library.app;

public class Project {
	private String description;
	private String name;
	private String id;
	
	public static void main(String[] args) {

	}
	public Project(String ID) {
		this.id = ID;
	}
	
	public String getId() {
		return this.id;
	}
	public String toString() {
		return "This project is named PLACEHOLDER with description PLACEHOLDER and id " + id;
	}
}

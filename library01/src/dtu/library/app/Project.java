package dtu.library.app;

import java.time.LocalDate;

public class Project {
	private String description;
	private String name;
	private String id;
	private LocalDate endDate;
	private LocalDate startDate;
	
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

    public String getName() {
		return name;
	}
    public void setDescription(String newDescription) {
    	description = newDescription;
    }

	public String getDescription() {
		return description;
	}

	public void setName(String name2) {
		name = name2;
		
	}

	public LocalDate getEndDate() {
		 return endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startProjectDate) {
		startDate = startProjectDate;
	}

	public void setEndDate(LocalDate endProjectDate) {
		endDate = endProjectDate;
	}
}

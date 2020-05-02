package dtu.library.app;

import java.util.ArrayList;
import dtu.library.app.timeInterface.datesInterface;
import java.time.LocalDate;

public class Project implements datesInterface {
    private String description, name, id;
    private LocalDate startDate, endDate;
    private ArrayList<Activity> activities;
    boolean isOverdue;

    public Project(String name, String id) {
        this.name = name;
        this.id = id;
        this.startDate = null;
        this.endDate = null;
        description = "";
        activities = new ArrayList<Activity>();
    }

	public String toString() {
		return "This project is named " + name + " with description" + description + " and id " + id;
	}

    public Project getProject() {
        return this;
    }

    public String getId() {
        return this.id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean isOverdue() {
		return endDate.compareTo(startDate) != 0 && endDate.compareTo(startDate) >= 0;
	}
    @Override
    public void setStartDate(LocalDate startProjectDate) {
    	System.out.println("STARTDATE WAS SET TO " + startProjectDate);
    	startDate = startProjectDate;
    }
    @Override
    public void setEndDate(LocalDate endProjectDate) {
    	System.out.println("ENDDATE WAS SET TO " + endProjectDate);
        endDate = endProjectDate;
    }

//	public boolean addActivity(String string) throws OperationNotAllowedException {
//		if (!hasActivity(string)) {
//			activities.add(new Activity(string));
//			System.out.println("Has added activity " + string + " to project " + name);
//			return true;
//		}
//		throw new OperationNotAllowedException("The project already has an activity with the name " + string);
//	}
//
//	public boolean hasActivity(String sA) {
//		for (Activity currentActivity : activities) {
//			String currentId = currentActivity.getName();
//			if (currentId.equals(sA)) {
//				return true;
//			}
//		}
//		return false;
//	}
}

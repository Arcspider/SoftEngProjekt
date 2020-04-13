package dtu.library.app;

import dtu.library.app.timeInterface.datesInterface;

import java.time.LocalDate;

public class Project implements datesInterface {
    private String description, name, id;
    private LocalDate startDate, endDate;
    boolean isOverdue;

    public Project(String Name, String ID) {
        this.name = Name;
        this.id = ID;
    }

    public Project getProject() {
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

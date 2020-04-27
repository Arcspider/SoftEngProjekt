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
        description = "Description yet to be written";
        activities = new ArrayList<Activity>();
    }

	public String toString() {
		return "This project is named \"" + name + "\" with the description \"" + description + "\" and id: \"" + id + "\"";
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
        startDate = startProjectDate;
    }
    @Override
    public void setEndDate(LocalDate endProjectDate) {
        endDate = endProjectDate;
    }

	public boolean addActivity(String activityName) throws OperationNotAllowedException {
		if (!hasActivity(activityName)) {
			activities.add(new Activity(activityName));
			System.out.println("Added activity " + activityName+ " to project " + this.name);
			return true;
		}
		throw new OperationNotAllowedException("The project already has an activity with the name " + name);
	}

	public boolean hasActivity(String activityName) {
		for (Activity currentActivity : activities) {
			String currentId = currentActivity.getName();
			if (currentId.equals(activityName)) {
				return true;
			}
		}
		return false;
	}

	public Activity getActivity(String activityName) {
		for (Activity currentActivity : activities) {
			String currentName = currentActivity.getName();
			if (currentName.equals(activityName)) {
				return currentActivity;
			}
		}
		return null;
	}

	public void setActivityStartDate(Activity currentActivity, LocalDate startDate) {
		currentActivity.setStartDate(startDate);
	}

	public void setActivityEndDate(Activity currentActivity, LocalDate newEnd) {
		currentActivity.setEndDate(newEnd);
	}
}

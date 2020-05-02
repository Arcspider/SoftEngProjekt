package dtu.library.app;

import java.util.ArrayList;
import dtu.library.app.timeInterface.datesInterface;
import java.time.LocalDate;

public class Project implements datesInterface {
    private String description, name, id;
    private LocalDate startDate, endDate;
    private ArrayList<Activity> activities;
    private ArrayList<Worker> workers;
    private Worker leader;
    boolean isOverdue;

    public Project(String name, String id) {
        this.name = name;
        this.id = id;
        this.startDate = null;
        this.endDate = null;
        leader = null;
        description = "Description yet to be written";
        activities = new ArrayList<Activity>();
        workers = new ArrayList<Worker>();
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

	public boolean addActivity(String name) throws OperationNotAllowedException {
		if (!hasActivity(name)) {
			activities.add(new Activity(name));
			System.out.println("Added activity " + name + " to project " + this.name);
			return true;
		}
		throw new OperationNotAllowedException("The project already has an activity with the name " + name);
	}

	public boolean hasActivity(String name) {
		for (Activity currentActivity : activities) {
			String currentId = currentActivity.getName();
			if (currentId.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public Activity getActivity(String name2) {
		for (Activity currentActivity : activities) {
			String currentId = currentActivity.getName();
			if (currentId.equals(name)) {
				return currentActivity;
			}
		}
		return null;
	}
	
	public boolean hasLeader() {
		if(leader != null) {
			return true;
		}
		return false;
	}
	
	public Worker getLeader() {
		return this.leader;
	}

	public boolean setLeader(Worker worker) throws OperationNotAllowedException {
		if(!hasLeader() && worker.exists()) {
			leader = worker;
			System.out.println(leader.getName() + " is now a Leader for the project " + this.name);
			return true;
		} else if(hasLeader() && getLeader().equals(worker)){
			throw new OperationNotAllowedException("This worker is already leader for the project");
		} else {
			throw new OperationNotAllowedException("This project already has a leader");		
		}
	}
	
}

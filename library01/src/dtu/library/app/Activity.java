package dtu.library.app;

import java.time.LocalDate;
import java.util.ArrayList;

import dtu.library.app.timeInterface.datesInterface;

public class Activity implements datesInterface{
	private String name, description;
	private ArrayList<Worker> workers;
	LocalDate startDate, endDate;

	public Activity(String name){
		this.name = name;
		workers = new ArrayList<>();
		startDate = null;
		endDate = null;
	}
	public String getName() {
		return name;
	}

	public boolean addWorker(String name, String id) throws OperationNotAllowedException {
		if (!hasWorker(name, id)) {
			workers.add(new Worker(name, id));
			System.out.println("Added worker: " + name +" with ID:" + id  + " to the activity " + this.name);
			return true;
		}
		throw new OperationNotAllowedException("The project already has an activity with the name " + name);
	}

	public boolean hasWorker(String name, String id) {
		for (Worker currentWorker : workers) {
			String currentId = currentWorker.getName();
			if (currentWorker.getName().equals(name) && currentId.equals(id)) {
				return true;
			}
		}
		return false;
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
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	@Override
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public void setName(String newActivityName) {
		this.name = newActivityName;
	}
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	public String getDescription() {
		return this.description;
	}
}

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
	
	public String toString() {
		return "This activity is named " + name + ", starts at " + startDate + " and ends at " + endDate;
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
	public void assignWorker(Worker worker) {
		workers.add(worker);
	}
}

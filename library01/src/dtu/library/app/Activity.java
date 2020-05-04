package dtu.library.app;

import java.time.LocalDate;
import java.util.ArrayList;

import dtu.library.app.timeInterface.datesInterface;

public class Activity implements datesInterface{
	private String name, description;
	private ArrayList<Worker> workers;
	private ArrayList<String> shifts;
	LocalDate startDate, endDate;
	private Double budgettedHoursTotal;
	private Double budgettedHoursLeft;

	public Activity(String name){
		this.name = name;
		workers = new ArrayList<>();
		startDate = null;
		endDate = null;
		this.shifts = new ArrayList<String>();
		budgettedHoursLeft = 0.0;
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
		System.out.println("Employee " + worker.getId() + " has been assigned");
	}
	
	public boolean hasWorker(Worker worker) {
		return workers.contains(worker);
	}
	
	public boolean hasAnyWorkers() {
		if(workers.size() > 0) {
			return true;
		}
		return false;
	}
	
	public String listWorkers() {
		if(hasAnyWorkers()) {
			int noOfWorkers = 0;
			for(int i = 0; i < workers.size(); i++) {
				System.out.println(workers.get(i).getId());
				noOfWorkers = i;
			}
			return "These were all the assigned workers";
		} else {
			return "There are no workers assigned";
		}
	}
	
	public boolean hasWorkerId(String id) {
		for(Worker worker : workers) {
			if(worker.getId().equals(id)) return true;
		}
		return false;
	}
	public void setBudgettedHours(String budgettedHours) {
		double tempDouble = Double.parseDouble(budgettedHours);
		if(tempDouble > budgettedHoursLeft) {
			this.budgettedHoursTotal = tempDouble;
		}else System.out.println("You can't reduce the budgetted hours below what is already assigned to the project.");
		
	}
	public Double getBudgettedHours() {
		return budgettedHoursTotal;
	}

	public void addShift(String fullDateFormat) {
		String[] separated = fullDateFormat.split(";");
		
		Double hours = Double.parseDouble(separated[2]);
		budgettedHoursLeft -= hours;
		shifts.add(fullDateFormat);		
	}
	
	public int findShiftByIdAndDate(String shift) {
		String[] shiftSplit = shift.split(";");
		for(int i = 0; i< shifts.size();i++) {
			String currentShift = shifts.get(i);
			String[] currentShiftSplit = currentShift.split(";");
			if(shiftSplit[0].equals(currentShiftSplit[0]) && shiftSplit[1].equals(currentShiftSplit[1])) {
				return i;
			}
		}System.out.println("No shifts found"); return 0;
	}
	public boolean hasShiftByIdAndDate(String shift) {
		String[] shiftSplit = shift.split(";");
		for(String currentShift : shifts) {
			String[] currentShiftSplit = currentShift.split(";");
			if(shiftSplit[0].equals(currentShiftSplit[0]) && shiftSplit[1].equals(currentShiftSplit[1])) {
				return true;
			}
		}System.out.println("No shifts found"); return false;
	}
	public ArrayList<String> getShifts() {
		return shifts;
	}
	public void setShift(int tempShift, String fullDateFormat) {
		shifts.set(tempShift, fullDateFormat);
	}
}

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
	public boolean hasWorker(Worker worker) {
		return workers.contains(worker);
	}
	//TODO find et alternativ til at have 2 forskellige hasWorker funktioner. Lige nu bruger timeTest den som bruger hasWorkerId, mens en helt anden fil bruger den almindelige hasWorker.
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
	public boolean hasShiftByIdAndDate(String shift) {
		String[] shiftSplit = shift.split(";");
		for(String currentShift : shifts) {
			String[] currentShiftSplit = shift.split(";");
			if(shiftSplit[0].equals(currentShiftSplit[0]) && shiftSplit[1].equals(currentShiftSplit[1])) {
				return true;
			}
		}System.out.println("No shifts found"); return false;
	}
}

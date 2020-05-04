package dtu.library.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import dtu.library.app.timeInterface.datesInterface;

public class Activity implements datesInterface{
	private String name, description;
	private ArrayList<Worker> workers;
	private ArrayList<Shift> shifts;
	LocalDate startDate, endDate;
	private Double budgettedHoursTotal;
	private Double budgettedHoursLeft;
	private Shift shift;

	public Activity(String name){
		this.name = name;
		workers = new ArrayList<>();
		startDate = null;
		endDate = null;
		this.shifts = new ArrayList<Shift>();
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

	public void addShift(String workerID, String stringDate, String time ) {
		boolean newShift = true;
		LocalDate date = stringToDate(stringDate);
		for(int i = 0; i <shifts.size(); i++) {
			if(shifts.get(i).getWorkerID().equals(workerID) && shifts.get(i).getDate().equals(date)) {
				shifts.get(i).addTime(Double.parseDouble(time));
				newShift = false;
				break;
			}
		}
		if(newShift) {
			shift = new Shift(workerID,date,Double.parseDouble(time));
			shifts.add(shift);
		}		
	}
	
	public Shift findShiftByIdAndDate(String workerID, String stringDate) {
		LocalDate date = stringToDate(stringDate);
		for(int i = 0; i <shifts.size(); i++) {
			if(shifts.get(i).getWorkerID().equals(workerID) && shifts.get(i).getDate().equals(date)) {
				return shifts.get(i);
			}
		}
		return null;
	}
	
	public boolean hasShiftByIdAndDate(String workerID, String stringDate) {
		LocalDate date = stringToDate(stringDate);
		for(int i = 0; i <shifts.size(); i++) {
			if(shifts.get(i).getWorkerID().equals(workerID) && shifts.get(i).getDate().equals(date)) {
				return true;
			}
		}
		return false;
	}
	
	public void getWorkerShifts(String stringDate) {
		LocalDate date = stringToDate(stringDate);
		for (int i = 0; i < shifts.size(); i++) {
		if (shifts.get(i).getDate().equals(date)) {
			System.out.println(shifts.get(i).toString());
		}
	}
	}

	
	public LocalDate stringToDate(String toBeConverted) {
		String[] stringDate = toBeConverted.split("-");
		int dInt = Integer.parseInt(stringDate[0]);
		int mInt = Integer.parseInt(stringDate[1]);
		int yInt = Integer.parseInt(stringDate[2]);

		Calendar cld = Calendar.getInstance();
		cld.set(Calendar.YEAR, yInt);
		cld.set(Calendar.MONTH, mInt);
		cld.set(Calendar.DAY_OF_MONTH, dInt);

		LocalDate finalDate = LocalDate.of(cld.get(Calendar.YEAR), cld.get(Calendar.MONTH),
				cld.get(Calendar.DAY_OF_MONTH));
		return finalDate;
	}

}

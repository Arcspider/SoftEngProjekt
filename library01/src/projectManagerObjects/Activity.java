package projectManagerObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import timeInterface.datesInterface;

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
		budgettedHoursTotal = 0.0;
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
		return workers.size() > 0;
	}

	public String listWorkers() {
		if(hasAnyWorkers()) {
			for(int i = 0; i < workers.size(); i++) {
				System.out.println(workers.get(i).getId());
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
		this.budgettedHoursTotal = Double.parseDouble(budgettedHours);

	}
	public Double getBudgettedHours() {
		return budgettedHoursLeft;
	}

	public void addShift(String workerID, String stringDate, String time ) {
		
		boolean newShift = true;
		LocalDate date = stringToDate(stringDate);
		for (Shift value : shifts) {
			if (value.getWorkerID().equals(workerID) && value.getDate().equals(date)) {
				
				value.addTime(Double.parseDouble(time));
				newShift = false;
				break;
			}
		}
		if(newShift) {
			shift = new Shift(workerID,date,Double.parseDouble(time));
			shifts.add(shift);
		}
		System.out.println(time + " hours have been added to " + workerID + " who will be working the " + stringDate);
		System.out.println("");
		updateTimeLeft();
	}

	public boolean hasShiftByIdAndDate(String workerID, String stringDate) {
		LocalDate date = stringToDate(stringDate);
		for (Shift value : shifts) {
			if (value.getWorkerID().equals(workerID) && value.getDate().equals(date)) {
				return true;
			}
		}
		return false;
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
	public ArrayList<Shift> getShifts() {
		return shifts;
	}
	public void updateTimeLeft() {
		budgettedHoursLeft = budgettedHoursTotal;
		for(Shift currentShift : shifts) {
			budgettedHoursLeft -= currentShift.getHours();
		}
	}

}

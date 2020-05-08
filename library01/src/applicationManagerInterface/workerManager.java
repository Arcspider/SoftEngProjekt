package applicationManagerInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import projectManagerObjects.Activity;
import projectManagerObjects.Worker;

public class workerManager {
    private ArrayList<Worker> workers;
    private Random random;

    private Worker worker;
	private ModelTime modelTime;

    public workerManager() {
    
    	this.modelTime = new ModelTime();

        workers = new ArrayList<Worker>();

        random = new Random();
    }

    public Worker createWorker(String firstname, String lastname) {
        String id = workerGenerateID(firstname, lastname);
        worker = new Worker(firstname, lastname, id);
        addWorker(worker);
        return worker;
    }

    private String workerGenerateID(String firstname, String lastname) {
        return "" + Character.toUpperCase(firstname.charAt(0)) + Character.toUpperCase(firstname.charAt(1)) + Character.toUpperCase(lastname.charAt(0)) +
                Character.toUpperCase(lastname.charAt(1));
    }

    public boolean workerHasID(String id) {
        for (Worker worker : workers) {
            if (worker.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Worker getWorker(String id) {
        for (Worker worker : workers) {
            if (worker.getId().equals(id)) {
                return worker;
            }
        }
        return null;
    }

    public void addWorker(Worker worker) {
        workers.add(worker);

    }

    public void assignWorker(Activity activity, Worker worker) {
        activity.assignWorker(worker);
        worker.assignWorker(activity);
    }

	public void assignAbsence(String userId, String absenceType, String startAbsence, String endAbsence) {
		Worker currentWorker = getWorker(userId);
		LocalDate startDate = modelTime.stringToDate(startAbsence);
		LocalDate endDate = modelTime.stringToDate(endAbsence);
		ArrayList<Activity> workersAssignedActivities = currentWorker.getActivities(); 
		for(Activity currentActivity : workersAssignedActivities ) {
			ArrayList<Shift> activityShifts = currentActivity.getShifts();
			for(Shift currentShift : activityShifts) {
				LocalDate shiftDate = currentShift.getDate();
				if(startDate.isBefore(shiftDate) && endDate.isAfter(shiftDate)) {
					currentShift.setTime(0.0);
				}
			}
			currentActivity.updateTimeLeft();
		}
	}

}

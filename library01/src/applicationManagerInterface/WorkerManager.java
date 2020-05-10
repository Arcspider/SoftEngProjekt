package applicationManagerInterface;
//Kasper(s194579), Søren(s194630), Jakob(s194623) har haft ansvaret for denne klasse.
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import projectManagerObjects.Activity;
import projectManagerObjects.Shift;
import projectManagerObjects.Worker;

public class WorkerManager {
    private ArrayList<Worker> workers;
    private Worker worker;
	private TimeManager modelTime;

    public WorkerManager() {
    
    	this.modelTime = new TimeManager();

        workers = new ArrayList<Worker>();

        new Random();
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
    
    public boolean setThisWorker(String id) {
    	if(workerHasID(id)) {
    		this.worker = getWorker(id);
    		return true;
    	}
    	return false;
    }

    public void assignWorker(Activity activity, Worker worker) {
        activity.assignWorker(worker);
        worker.assignWorker(activity);
    }

	public void assignAbsence(String userId, String startAbsence, String endAbsence) {
		Worker currentWorker = getWorker(userId);
		LocalDate startDate = modelTime.stringToDate(startAbsence);
		LocalDate endDate = modelTime.stringToDate(endAbsence);
		ArrayList<Activity> workersAssignedActivities = currentWorker.getActivities(); 
		for(Activity currentActivity : workersAssignedActivities ) {
			ArrayList<Shift> activityShifts = currentActivity.getShifts();
			for(int i = 0; i< activityShifts.size(); i++) {
				Shift currentShift = activityShifts.get(i);
				LocalDate shiftDate = currentShift.getDate();
				if(startDate.isBefore(shiftDate) && endDate.isAfter(shiftDate)) {
					currentActivity.removeShift(currentShift.getWorkerID(),currentShift.getDate());
				}
			}
			currentActivity.updateTimeLeft();
		}
	}

	public Worker getThisWorker() {
		return this.worker;
	}

}

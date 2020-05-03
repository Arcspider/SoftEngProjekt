package dtu.library.app;
import java.util.ArrayList;
import java.util.Random;

public class ModelWorker {
	private ArrayList<Worker> workers;
	private Random random;

	private Worker worker;

	public ModelWorker() {
	

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
		String id = "" + firstname.charAt(0) + lastname.charAt(0) + random.nextInt(100);
		while (workerHasID(id))
			id = "" + firstname.charAt(0) + firstname.charAt(1) + lastname.charAt(0) + lastname.charAt(1) ;
		return id;
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

	}	public boolean assignWorker(Activity activity, Worker worker) {
		activity.assignWorker(worker);
		worker.assignWorker(activity);
		return true;
	}

}

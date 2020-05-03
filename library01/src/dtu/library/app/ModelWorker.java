package dtu.library.app;
import java.util.ArrayList;
import java.util.Random;

public class ModelWorker {
	private View view;
	private ArrayList<Worker> workers;
	private Random random;

	private Worker worker;

	public ModelWorker(View view) {
		this.view = view;

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
		String id = "" + Character.toUpperCase(firstname.charAt(0)) + Character.toUpperCase(firstname.charAt(1)) + Character.toUpperCase(lastname.charAt(0)) +
				Character.toUpperCase(lastname.charAt(1));
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

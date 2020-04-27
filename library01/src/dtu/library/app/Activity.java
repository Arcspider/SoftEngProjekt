package dtu.library.app;

import java.util.ArrayList;

public class Activity {
	private String name;
	private ArrayList<Worker> workers;
	public Activity(String name){
		this.name = name;
		workers = new ArrayList<>();
	}
	public String getName() {
		return name;
	}

//	public boolean addWorker(String name, String id) throws OperationNotAllowedException {
//		if (!hasWorker(name, id)) {
//			workers.add(new Worker(name, id));
//			System.out.println("Added worker: " + name +" with ID:" + id  + " to the activity " + this.name);
//			return true;
//		}
//		throw new OperationNotAllowedException("The project already has an activity with the name " + name);
//	}
//
//	public boolean hasWorker(String name, String id) {
//		for (Worker currentWorker : workers) {
//			String currentId = currentWorker.getName();
//			if (currentWorker.getName().equals(name) && currentId.equals(id)) {
//				return true;
//			}
//		}
//		return false;
//	}
}

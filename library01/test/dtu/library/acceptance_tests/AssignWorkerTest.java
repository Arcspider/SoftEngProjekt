package dtu.library.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;
import projectManagerObjects.Worker;

import static org.junit.Assert.assertTrue;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;

public class AssignWorkerTest {


	private ProjectManager projectManager;
	private ActivityManager activityManager;
	private WorkerManager workerManager;
	private Project project;
	private Activity activity;
	private Worker worker;


	public AssignWorkerTest(View view,ProjectManager projectManager,ActivityManager activityManager,WorkerManager workerManager, ErrorMessageHolder errorMessageHolder) {
		
		this.projectManager = projectManager;
		this.activityManager = activityManager;
		this.workerManager = workerManager;
	}
	@Given("a project with  id {string}")
	public void aProjectWithId(String projectID) throws OperationNotAllowedException {
		project = projectManager.createProject("Tom");
		project.setId(projectID);
		projectManager.addProject(project);
	}

	@Given("has the activity {string}")
	public void hasTheActivity(String activityName) throws OperationNotAllowedException {
		 assertTrue(activityManager.addActivity(project, activityName));
		 activity = activityManager.getActivity(project, activityName);
	}

	@Given("the worker with id {string} exists'")
	public void theWorkerWithIdExists(String workerID) {
	    worker = workerManager.createWorker("Tom", "Bob");
	    worker.setID(workerID);
	    workerManager.addWorker(worker);
	}
	@Then("the user assign the worker to the activity")
	public void theUserAssignTheWorkerToTheActivity() {
		workerManager.assignWorker(activity,worker);
		assertTrue(activity.hasWorker(worker));
	}
}
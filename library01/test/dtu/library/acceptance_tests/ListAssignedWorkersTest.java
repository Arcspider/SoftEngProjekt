package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.Worker;

public class ListAssignedWorkersTest {

	private ProjectManager projectManager;
	private ActivityManager activityManager;
	private WorkerManager workerManager;
	private ErrorMessageHolder errorMessageHolder;
	private Worker Bob;
	private Worker Alice;
	private Project project;
	private Activity activity;
	private String name;

	public ListAssignedWorkersTest(ProjectManager projectManager, ActivityManager activityManager,  WorkerManager workerManager ,ErrorMessageHolder errorMessageHolder) {
		this.projectManager = projectManager;
		this.activityManager = activityManager;
		this.workerManager = workerManager;
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("an activity {string} has workers assigned to it")
	public void anActivityHasWorkersAssignedToIt(String string) throws OperationNotAllowedException {
		Bob = workerManager.createWorker("Bob", "Test");
		Alice = workerManager.createWorker("Alice", "Test");
	    project = projectManager.createProject("Coolness");
	    project.addActivity(string);
	    activity = project.getActivity(string);
	    activity.assignWorker(Bob);
	    activity.assignWorker(Alice);

	    assertTrue(activity.hasAnyWorkers());
	}

	@Then("the user can get a list of the workers")
	public void theUserCanGetAListOfTheWorkers() {
		assertEquals(activity.listWorkers(), "These were all the assigned workers");
	}

	@Given("an activity {string} that has no workers assigned to it")
	public void anActivityThatHasNoWorkersAssignedToIt(String string) throws OperationNotAllowedException {
		project = projectManager.createProject("Coolness");
	    project.addActivity(string);
	    activity = project.getActivity(string);

	    assertFalse(activity.hasAnyWorkers());
	}


	@Then("the message {string} is displayed")
	public void theMessageIsDisplayed(String string) {
	    assertEquals(activity.listWorkers(), "There are no workers assigned");
	}

}

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


	private ProjectManager modelProject;
	private ActivityManager modelActivity;
	private WorkerManager modelWorker;
	private Project project;
	private Activity activity;
	private Worker worker;


	public AssignWorkerTest(View view,ProjectManager modelProject,ActivityManager modelActivity,WorkerManager modelWorker, ErrorMessageHolder errorMessageHolder) {
		
		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.modelWorker = modelWorker;
	}
	@Given("a project with  id {string}")
	public void aProjectWithId(String projectID) throws OperationNotAllowedException {
		project = modelProject.createProject("Tom");
		project.setId(projectID);
		modelProject.addProject(project);
	}

	@Given("has the activity {string}")
	public void hasTheActivity(String activityName) throws OperationNotAllowedException {
		 assertTrue(modelActivity.addActivity(project, activityName));
		 activity = modelActivity.getActivity(project, activityName);
	}

	@Given("the worker with id {string} exists'")
	public void theWorkerWithIdExists(String workerID) {
	    worker = modelWorker.createWorker("Tom", "Bob");
	    worker.setID(workerID);
	    modelWorker.addWorker(worker);
	}
	@Then("the user assign the worker to the activity")
	public void theUserAssignTheWorkerToTheActivity() {
		modelWorker.assignWorker(activity,worker);
		assertTrue(activity.hasWorker(worker));
	}
}
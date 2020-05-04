package dtu.library.acceptance_tests;

import dtu.library.app.Activity;
import dtu.library.app.ModelActivity;
import dtu.library.app.ModelProject;
import dtu.library.app.ModelWorker;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.Worker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertTrue;

public class AssignWorkerTest {


	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ModelWorker modelWorker;
	private Project project;
	private Activity activity;
	private Worker worker;


	public AssignWorkerTest(View view,ModelProject modelProject,ModelActivity modelActivity,ModelWorker modelWorker, ErrorMessageHolder errorMessageHolder) {
		
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
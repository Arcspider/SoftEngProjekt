package dtu.library.acceptance_tests;

import dtu.library.app.Activity;
import dtu.library.app.Controller;
import dtu.library.app.Model;
import dtu.library.app.ModelActivity;
import dtu.library.app.ModelProject;
import dtu.library.app.ModelWorker;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.Worker;
import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;
import dtu.library.app.controllerInterface.ControllerWorker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class AssignWorkerTest {

	private Model model; 
	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ModelWorker modelWorker;
	private ErrorMessageHolder errorMessageHolder;

	private Project project;
	private Activity activity;
	private Worker worker;


	public AssignWorkerTest(View view, Model model,ModelProject modelProject,ModelActivity modelActivity,ModelWorker modelWorker, ErrorMessageHolder errorMessageHolder) {
		this.model = model;
		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.modelWorker = modelWorker;
		this.errorMessageHolder = errorMessageHolder;
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
		assertTrue(modelWorker.assignWorker(activity,worker));
	}
}
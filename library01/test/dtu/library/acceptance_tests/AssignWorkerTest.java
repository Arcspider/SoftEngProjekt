package dtu.library.acceptance_tests;

import dtu.library.app.Activity;
import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.Worker;
import dtu.library.app.controllerInterface.Controller;
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
	private ErrorMessageHolder errorMessageHolder;

	private Project project;
	private Activity activity;
	private Worker worker;


	public AssignWorkerTest(View view, Model model, ErrorMessageHolder errorMessageHolder) {
		this.model = model;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("a project with  id {string}")
	public void aProjectWithId(String projectID) throws OperationNotAllowedException {
		project = model.createProject("Tom");
		project.setId(projectID);
		model.addProject(project);
	}
	
	@Given("has the activity {string}")
	public void hasTheActivity(String activityName) throws OperationNotAllowedException {
		 assertTrue(model.addActivity(project, activityName));
		 activity = model.getActivity(project, activityName);
	}
	
	@Given("the worker with id {string} exists'")
	public void theWorkerWithIdExists(String workerID) {
	    worker = model.createWorker("Tom", "Bob");
	    worker.setID(workerID);
	    model.addWorker(worker);
	}
	@Then("the user assign the worker to the activity")
	public void theUserAssignTheWorkerToTheActivity() {
		assertTrue(model.assignWorker(activity,worker));
	}
}
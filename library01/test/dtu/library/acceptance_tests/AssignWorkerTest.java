package dtu.library.acceptance_tests;

import dtu.library.app.Activity;
import dtu.library.app.Controller;
import dtu.library.app.Model;
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
	private ErrorMessageHolder errorMessageHolder;

	private Project project;
	private Activity activity;
	private Worker worker;
	private String ID;
	private List<Project> projects;

	public AssignWorkerTest(View view, Model model, ErrorMessageHolder errorMessageHolder) {
		this.model = model;
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("that a worker with the name {string} exists")
	public void thatAWorkerWithTheNameExists(String string) {
		String[] name = string.split(" ");
		worker = model.createWorker(name[0], name[1]);
	}
	@Given("that the activity {string} exists")
	public void thatTheActivityExists(String string) throws OperationNotAllowedException {
	    project = model.createProject("tom");
	    assertTrue(model.addActivity(project, string));
	    activity = model.getActivity(project,string);
	}

	@When("the user chooses the worker and the user chooses the activity")
	public void theUserChoosesTheWorkerAndTheUserChoosesTheActivity() {
		assertTrue(model.workeHasID(worker.getId()));
	}
}
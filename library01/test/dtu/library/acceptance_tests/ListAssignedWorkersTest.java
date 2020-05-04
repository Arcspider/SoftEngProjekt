package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import io.cucumber.java.en.When;

public class ListAssignedWorkersTest {
	
	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ModelWorker modelWorker;
	private ErrorMessageHolder errorMessageHolder;
	private Worker Bob;
	private Worker Alice;
	private Project project;
	private Activity activity;
	private String name;
	
	public ListAssignedWorkersTest(ModelProject modelProject, ModelActivity modelActivity,  ModelWorker modelWorker ,ErrorMessageHolder errorMessageHolder) {
		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.modelWorker = modelWorker;
		this.errorMessageHolder = errorMessageHolder; 
	}

	@Given("an activity {string} has workers assigned to it")
	public void anActivityHasWorkersAssignedToIt(String string) throws OperationNotAllowedException {
		Bob = modelWorker.createWorker("Bob", "Test");
		Alice = modelWorker.createWorker("Alice", "Test");
	    project = modelProject.createProject("Coolness");
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
		project = modelProject.createProject("Coolness");
	    project.addActivity(string);
	    activity = project.getActivity(string);
	    
	    assertFalse(activity.hasAnyWorkers());
	}
	

	@Then("the message {string} is displayed")
	public void theMessageIsDisplayed(String string) {
	    assertEquals(activity.listWorkers(), "There are no workers assigned");
	}
	
}

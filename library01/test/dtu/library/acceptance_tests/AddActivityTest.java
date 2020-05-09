package dtu.library.acceptance_tests;

import static org.junit.Assert.assertTrue;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import io.cucumber.java.en.*;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;
import projectManagerObjects.Worker;

public class AddActivityTest {

    private ProjectManager projectManager;
    private ActivityManager activityManager;
    private WorkerManager workerManager;
    private ErrorMessageHolder errorMessageHolder;
    private String name;

    private Project project;
    private Worker worker;

    Activity newActivity;

    public AddActivityTest(View view,ProjectManager projectManager, ActivityManager activityManager, WorkerManager workerManager,ErrorMessageHolder errorMessageHolder){
    	this.projectManager = projectManager;
    	this.activityManager = activityManager;
    	this.workerManager = workerManager;
    	this.errorMessageHolder  = errorMessageHolder;
    }

	@Given("a project with id {string}")
	public void aProjectWithId(String string) throws OperationNotAllowedException {
		project = projectManager.createProject("tom");
		project.setId(string);
	    projectManager.addProject(project);
	    worker = workerManager.createWorker("Bob", "tom");
	    projectManager.setLeader(project, worker);
	}
	@Given("the project has a leader")
	public void theProjectHasALeader() {
		assertTrue(projectManager.hasLeader(project));
	}


	@Given("the user adds activity {string}")
	public void theUserAddsActivity(String string) throws OperationNotAllowedException {
		assertTrue(activityManager.addActivity(project,string));
	}

	@Then("the activity {string} is added to {string}")
	public void theActivityIsAddedTo(String sA, String sP) {
	  assertTrue(activityManager.activityExists(projectManager.getProject(sP),sA));
	}

	@Given("a project {string}")
	public void aProject(String string) throws OperationNotAllowedException {
		project = projectManager.createProject("tom");
		project.setId(string);
	    projectManager.addProject(project);
	}
	@Given("the project contains the activity {string}")
	public void theProjectContainsTheActivity(String string) throws OperationNotAllowedException {
		assertTrue(activityManager.addActivity(project,string));
	}

	@When("the user adds a activity {string}")
	public void theUserAddsAActivity(String string) {
		name = string;
	}
	@Then("an error message {string} is given")
	public void anErrorMessageIsGiven(String string) {
		try {
			activityManager.addActivity(project,name);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

}

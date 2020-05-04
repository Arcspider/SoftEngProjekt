package dtu.library.acceptance_tests;

import static org.junit.Assert.assertTrue;

import dtu.library.app.Activity;
import dtu.library.app.ModelActivity;
import dtu.library.app.ModelProject;
import dtu.library.app.ModelWorker;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.Worker;
import io.cucumber.java.en.*;

public class AddActivityTest {

    private ModelProject modelProject;
    private ModelActivity modelActivity;
    private ModelWorker modelWorker;
    private ErrorMessageHolder errorMessageHolder;
    private String name;

    private Project project;
    private Worker worker;

    Activity newActivity;

    public AddActivityTest(View view,ModelProject modelProject, ModelActivity modelActivity, ModelWorker modelWorker,ErrorMessageHolder errorMessageHolder){
    	this.modelProject = modelProject;
    	this.modelActivity = modelActivity;
    	this.modelWorker = modelWorker;
    	this.errorMessageHolder  = errorMessageHolder;
    }

	@Given("a project with id {string}")
	public void aProjectWithId(String string) throws OperationNotAllowedException {
		project = modelProject.createProject("tom");
		project.setId(string);
	    modelProject.addProject(project);
	    worker = modelWorker.createWorker("Bob", "tom");
	    modelProject.setLeader(project, worker);
	}
	@Given("the project has a leader")
	public void theProjectHasALeader() {
		assertTrue(modelProject.hasLeader(project));
	}


	@Given("the user adds activity {string}")
	public void theUserAddsActivity(String string) throws OperationNotAllowedException {
		assertTrue(modelActivity.addActivity(project,string));
	}

	@Then("the activity {string} is added to {string}")
	public void theActivityIsAddedTo(String sA, String sP) {
	  assertTrue(modelActivity.activityExists(modelProject.getProject(sP),sA));
	}

	@Given("a project {string}")
	public void aProject(String string) throws OperationNotAllowedException {
		project = modelProject.createProject("tom");
		project.setId(string);
	    modelProject.addProject(project);
	}
	@Given("the project contains the activity {string}")
	public void theProjectContainsTheActivity(String string) throws OperationNotAllowedException {
		assertTrue(modelActivity.addActivity(project,string));
	}

	@When("the user adds a activity {string}")
	public void theUserAddsAActivity(String string) {
		name = string;
	}
	@Then("an error message {string} is given")
	public void anErrorMessageIsGiven(String string) {
		try {
			modelActivity.addActivity(project,name);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

}

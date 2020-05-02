package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dtu.library.app.Activity;
import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.Controller;
import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.*;

public class AddActivityTest {

    private Model model;
    private ErrorMessageHolder errorMessageHolder;
    private String name;

    private Project project;
    Activity newActivity;

    public AddActivityTest(View view,Model model,ErrorMessageHolder errorMessageHolder){
    	this.model = model;
    	this.errorMessageHolder  = errorMessageHolder;
    }

	@Given("a project with id {string}")
	public void aProjectWithId(String string) throws OperationNotAllowedException {
		project = model.createProject("tom"); 
		project.setId(string);
	    model.addProject(project);
	}

	@Given("the user adds activity {string}")
	public void theUserAddsActivity(String string) throws OperationNotAllowedException {
		assertTrue(model.addActivity(project,string));
	}

	@Then("the activity {string} is added to {string}")
	public void theActivityIsAddedTo(String sA, String sP) {
	  assertTrue(model.activityExists(model.getProject(sP),sA));
	}

	@Given("a project {string}")
	public void aProject(String string) throws OperationNotAllowedException {
		project = model.createProject("tom");
		project.setId(string);
	    model.addProject(project);
	}
	@Given("the project contains the activity {string}")
	public void theProjectContainsTheActivity(String string) throws OperationNotAllowedException {
		assertTrue(model.addActivity(project,string));
	}

	@When("the user adds a activity {string}")
	public void theUserAddsAActivity(String string) {
		name = string;
	}
	@Then("an error message {string} is given")
	public void anErrorMessageIsGiven(String string) {
		try {
			model.addActivity(project,name);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

}

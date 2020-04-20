package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dtu.library.app.Activity;
import dtu.library.app.Controller;
import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.*;

public class AddActivityTest {

    private View view;
    private Model model;
    private Controller controller;
    private ControllerProject controllerProject;
    private ControllerActivity controllerActivity;
    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    Activity newActivity;

    public AddActivityTest(View view,Model model,ErrorMessageHolder errorMessageHolder,Controller controller,ControllerProject controllerProject,ControllerActivity controllerActivity){
    	this.view = view;
    	this.model = model;
    	this.errorMessageHolder  = errorMessageHolder;
    	this.controller = controller;
    	this.controllerProject = controllerProject;
    	this.controllerActivity = controllerActivity;
    }

	@Given("a project with id {string}")
	public void aProjectWithId(String string) throws OperationNotAllowedException {
		project = controller.createProject("tom");
		project.setId(string);
	    controller.addProject(project);
	
	}

	@Given("the user adds activity {string}")
	public void theUserAddsActivity(String string) throws OperationNotAllowedException {
		assertTrue(controllerActivity.addActivity(string,project));
	}

	@Then("the activity {string} is added to {string}")
	public void theActivityIsAddedTo(String sA, String sP) {
	  assertTrue(controllerActivity.activityExists(model.getProject(sP),sA));
	}

	@Given("a project {string}")
	public void aProject(String string) throws OperationNotAllowedException {
		project = controller.createProject("tom");
		project.setId(string);
	    controller.addProject(project);
	}
	@Given("the project contains the activity {string}")
	public void theProjectContainsTheActivity(String string) throws OperationNotAllowedException {
		assertTrue(controllerActivity.addActivity(string,project));
	}

	@When("the user adds a activity {string}")
	public void theUserAddsAActivity(String string) {
		try {
			controllerActivity.addActivity(string,project);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

}

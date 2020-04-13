package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dtu.library.app.Activity;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerActivity;
import io.cucumber.java.en.*;

public class AddActivityTest {

//    private View view;
//    private ControllerActivity controllerActivity;
//    private ErrorMessageHolder errorMessageHolder;
//
//    private Project project;
//    Activity newActivity;
//
//    public AddActivityTest(View view,ErrorMessageHolder errorMessageHolder,ControllerActivity controllerActivity){
//    	this.view = view;
//    	this.errorMessageHolder  = errorMessageHolder;
//    	this.controllerActivity = controllerActivity;
//    }
//
//	@Given("a project with id {string}")
//	public void aProjectWithId(String string) throws OperationNotAllowedException {
//	    project = new Project("Tom", string);
//	    controllerActivity.addProject(project);
//	    assertTrue(controllerActivity.hasProject(string));
//	}
//
//	@Given("the user adds activity {string}")
//	public void theUserAddsActivity(String string) throws OperationNotAllowedException {
//		assertTrue(controllerActivity.addActivity(string,project));
//	}
//
//	@Then("the activity {string} is added to {string}")
//	public void theActivityIsAddedTo(String sA, String sP) {
//	  assertTrue(controllerActivity.hasActivity(sA,sP));
//	}
//
//	@Given("a project {string}")
//	public void aProject(String string) throws OperationNotAllowedException {
//	    project = new Project("Tom", string);
//	    controllerActivity.addProject(project);
//	    assertTrue(controllerActivity.hasProject(string));
//	}
//	@Given("the project contains the activity {string}")
//	public void theProjectContainsTheActivity(String string) throws OperationNotAllowedException {
//		assertTrue(controllerActivity.addActivity(string,project));
//	}
//
//	@When("the user adds a activity {string}")
//	public void theUserAddsAActivity(String string) {
//		try {
//			controllerActivity.addActivity(string,project);
//		} catch (OperationNotAllowedException e) {
//			errorMessageHolder.setErrorMessage(e.getMessage());
//		}
//	}

//	@Then("an error message {string} is given")
//	public void anErrorMessageIsGiven(String errorMessage) {
//		assertEquals(errorMessage,this.errorMessageHolder.getErrorMessage());
//	}

}

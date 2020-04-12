package dtu.library.acceptance_tests;

import static org.junit.Assert.assertTrue;

import dtu.library.app.Model;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerActivity;
import io.cucumber.java.en.*;

public class AddActivityTest {
	private View view;
	private ControllerActivity controllerActivity;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	private Model model;

	public AddActivityTest(View view, ControllerActivity controllerActivity, ErrorMessageHolder errorMessageHolder,Model model) {
		this.view = view;
		this.controllerActivity = controllerActivity;
		this.errorMessageHolder = errorMessageHolder;
		this.model = model;

	}

	@Given("a project with id {string}")
	public void aProjectWithId(String string) {
		project = new Project("tom", string);
		String id = project.getId();
		System.out.println(controllerActivity.exists(id));
		assertTrue(controllerActivity.exists(id));
	}

//
//	@Given("the user adds activity {string}")
//	public void theUserAddsActivity(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("the activity {string} is added to {string}")
//	public void theActivityIsAddedTo(String string, String string2) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("a project {string}")
//	public void aProject(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("the user is a leader for {string}")
//	public void theUserIsALeaderFor(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@When("the user adds activity {string}")
//	public void theUserAddsActivity(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@When("{string} has a activity {string}")
//	public void hasAActivity(String string, String string2) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("an error message {string} is given")
//	public void anErrorMessageIsGiven(String string) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
}

package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import dtu.library.app.Activity;
import dtu.library.app.Controller;
import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.*;

public class editActivityTest {

	private View view;
	private Model model;
	private Controller controller;
	private ControllerProject controllerProject;
	private ControllerActivity controllerActivity;
	private ErrorMessageHolder errorMessageHolder;
	private String name;

	private Project project;
	private Activity currentActivity;
	private String startDate,endDate;
	Activity newActivity;

	public editActivityTest(View view, Model model, ErrorMessageHolder errorMessageHolder, Controller controller,
			ControllerProject controllerProject, ControllerActivity controllerActivity) {
		this.view = view;
		this.model = model;
		this.errorMessageHolder = errorMessageHolder;
		this.controller = controller;
		this.controllerProject = controllerProject;
		this.controllerActivity = controllerActivity;
	}
	@Given("a project with id {string} has and activity {string}")
	public void aProjectWithIdHasAndActivity(String id, String name) throws OperationNotAllowedException {
		project = controller.createProject("TESTNAME");
		project.setId(id);
		controller.addProject(project);
		controllerActivity.addActivity(name, project);
		assertTrue(project.hasActivity(name));
	}
	@When("the user inputs start date {string} and end date {string}")
	public void theUserInputsStartDateAndEndDate(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		assertTrue((controllerActivity.validDate(startDate) && controllerActivity.validDate(endDate)));
	}

	@Then("set the start to {string} and end date to {string} for the activity {string}")
	public void setTheStartToAndEndDateToForTheActivity(String startDate, String endDate, String activity) throws OperationNotAllowedException {
		project = controller.createProject("TESTNAME");
		project.setId("030303");
		controller.addProject(project);
		controllerActivity.addActivity(activity, project);


		controllerActivity.setActivityStart(project,activity, startDate);
		controllerActivity.setActivityEnd(project,activity, endDate);
		currentActivity = model.getActivity(project, activity);
		assertEquals(model.stringToDate(startDate), currentActivity.getStartDate());
		assertEquals(model.stringToDate(endDate), currentActivity.getEndDate());


	}
	@Then("an error message {string} is given for the activity")
	public void anErrorMessageIsGivenForTheActivity(String string) throws OperationNotAllowedException {
		project = controller.createProject("TESTNAME");
		project.setId("030303");
		controller.addProject(project);
		controllerActivity.addActivity("testActivity", project);
		currentActivity = model.getActivity(project, "testActivity");

		try {
			model.setActivityStart(project, startDate, "testActivity");
			model.setActivityEnd(project, endDate, "testActivity");
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
			assertEquals(errorMessageHolder.getErrorMessage(), string);
		}

	}
}





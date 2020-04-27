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

	private Model model;
	private ErrorMessageHolder errorMessageHolder;
	private String name;

	private Project project;
	private Activity currentActivity;
	private String startDate,endDate;
	Activity newActivity;

	public editActivityTest(View view, Model model, ErrorMessageHolder errorMessageHolder) {
		this.model = model;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("a project with id {string} has and activity {string}")
	public void aProjectWithIdHasAndActivity(String id, String name) throws OperationNotAllowedException {
		project = model.createProject("TESTNAME");
		project.setId(id);
		model.addProject(project);
		model.addActivity(project, name);
		assertTrue(project.hasActivity(name));
	}
	@When("the user inputs start date {string} and end date {string}")
	public void theUserInputsStartDateAndEndDate(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		assertTrue((model.verifyDateFormat(startDate) && model.verifyDateFormat(endDate)));
	}

	@Then("set the start to {string} and end date to {string} for the activity {string}")
	public void setTheStartToAndEndDateToForTheActivity(String startDate, String endDate, String activity) throws OperationNotAllowedException {
		project = model.createProject("TESTNAME");
		project.setId("030303");
		model.addProject(project);
		model.addActivity(project, activity);


		model.setActivityStart(project,model.getActivity(project, activity), startDate);
		model.setActivityEnd(project,model.getActivity(project, activity), endDate);
		currentActivity = model.getActivity(project, activity);
		assertEquals(model.stringToDate(startDate), currentActivity.getStartDate());
		assertEquals(model.stringToDate(endDate), currentActivity.getEndDate());


	}
	@Then("an error message {string} is given for the activity")
	public void anErrorMessageIsGivenForTheActivity(String string) throws OperationNotAllowedException {
		project = model.createProject("TESTNAME");
		project.setId("030303");
		model.addProject(project);
		model.addActivity(project, "testActivity");
		currentActivity = model.getActivity(project, "testActivity");

		try {
			model.setActivityStart(project, currentActivity,startDate);
			model.setActivityEnd(project,currentActivity ,endDate);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
			assertEquals(errorMessageHolder.getErrorMessage(), string);
		}
	}

	@When("the user chooses the activity {string}")
	public void theUserChoosesTheActivity(String activityName) throws OperationNotAllowedException {
		project = model.createProject("TESTNAME");
		project.setId("030303");
		model.addProject(project);
		model.addActivity(project, activityName);
		currentActivity = model.getActivity(project, activityName);

		assertEquals(currentActivity.getName(), activityName);
	}

	@When("the user changes the activity name to {string}")
	public void theUserChangesTheActivityNameTo(String activityName) {
		assertTrue(model.verifyLegalActivityName(project,activityName));

	}

	@Then("the activitys name is changed to {string}")
	public void theActivitysNameIsChangedTo(String newActivityName) {
		try{
			model.changeActivityName(project,currentActivity.getName(),newActivityName);
			assertEquals(currentActivity.getName(), newActivityName);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the user changes the activity description to {string}")
	public void theUserChangesTheActivityDescriptionTo(String description) {
		assertTrue(model.legalDescription(description));
	}

	@Then("the activitys description is changed to {string}")
	public void theActivitysDescriptionIsChangedTo(String newDescription) {
		try{
			model.changeActivityDescription(project,currentActivity.getName(),newDescription);
			assertEquals(currentActivity.getDescription(), newDescription);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
}

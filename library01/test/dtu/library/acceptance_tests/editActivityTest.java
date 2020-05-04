package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import dtu.library.app.Activity;
import dtu.library.app.ModelActivity;
import dtu.library.app.ModelProject;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerActivity;
import dtu.library.app.controllerInterface.ControllerApplication;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.*;

public class editActivityTest {

	
	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ErrorMessageHolder errorMessageHolder;
	private String name;

	private Project project;
	private Activity currentActivity;
	private String startDate,endDate;
	Activity newActivity;

	public editActivityTest(View view,ModelProject modelProject, ModelActivity modelActivity ,ErrorMessageHolder errorMessageHolder) {
		
		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("a project with id {string} has and activity {string}")
	public void aProjectWithIdHasAndActivity(String id, String name) throws OperationNotAllowedException {
		project = modelProject.createProject("TESTNAME");
		project.setId(id);
		modelProject.addProject(project);
		modelActivity.addActivity(project, name);
		assertTrue(project.hasActivity(name));
	}
	@When("the user inputs start date {string} and end date {string}")
	public void theUserInputsStartDateAndEndDate(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		assertTrue((modelActivity.verifyDateFormat(startDate) && modelActivity.verifyDateFormat(endDate)));
	}

	@Then("set the start to {string} and end date to {string} for the activity {string}")
	public void setTheStartToAndEndDateToForTheActivity(String startDate, String endDate, String activity) throws OperationNotAllowedException {
		project = modelProject.createProject("TESTNAME");
		project.setId("030303");
		modelProject.addProject(project);
		modelActivity.addActivity(project, activity);
		currentActivity = modelActivity.getActivity(project, activity);

		modelActivity.setActivityStart(project,currentActivity, startDate);
		modelActivity.setActivityEnd(project,currentActivity, endDate);
		assertEquals(modelActivity.stringToDate(startDate), currentActivity.getStartDate());
		assertEquals(modelActivity.stringToDate(endDate), currentActivity.getEndDate());


	}
	@Then("an error message {string} is given for the activity")
	public void anErrorMessageIsGivenForTheActivity(String string) throws OperationNotAllowedException {
		project = modelProject.createProject("TESTNAME");
		project.setId("030303");
		modelProject.addProject(project);
		modelActivity.addActivity(project, "testActivity");
		currentActivity = modelActivity.getActivity(project, "testActivity");

		try {
			modelActivity.setActivityStart(project, currentActivity,startDate);
			modelActivity.setActivityEnd(project,currentActivity ,endDate);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
			assertEquals(errorMessageHolder.getErrorMessage(), string);
		}
	}

	@When("the user chooses the activity {string}")
	public void theUserChoosesTheActivity(String activityName) throws OperationNotAllowedException {
		project = modelProject.createProject("TESTNAME");
		project.setId("030303");
		modelProject.addProject(project);
		modelActivity.addActivity(project, activityName);
		currentActivity = modelActivity.getActivity(project, activityName);

		assertEquals(currentActivity.getName(), activityName);
	}

	@When("the user changes the activity name to {string}")
	public void theUserChangesTheActivityNameTo(String activityName) {
		assertTrue(modelActivity.verifyLegalActivityName(project,activityName));

	}

	@Then("the activitys name is changed to {string}")
	public void theActivitysNameIsChangedTo(String newActivityName) {
		try{
			modelActivity.changeActivityName(project,currentActivity,newActivityName);
			assertEquals(currentActivity.getName(), newActivityName);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the user changes the activity description to {string}")
	public void theUserChangesTheActivityDescriptionTo(String description) {
		assertTrue(modelActivity.legalDescription(description));
	}

	@Then("the activitys description is changed to {string}")
	public void theActivitysDescriptionIsChangedTo(String newDescription) {
		try{
			modelActivity.changeActivityDescription(project,currentActivity,newDescription);
			assertEquals(currentActivity.getDescription(), newDescription);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@When("the user changes its budgetted time to {string}")
	public void theUserChangesItsBudgettedTimeTo(String budgettedHours) {

		assertTrue(modelActivity.stringIsInteger(budgettedHours));
	}
	@Then("the activity's budgetted time is set to {string}")
	public void theActivitySBudgettedTimeIsSetTo(String budgettedHours) {
		modelActivity.setBudgettedHours(currentActivity,budgettedHours);
		//assertEquals(currentActivity.getBudgettedHours(), Integer.parseInt(budgettedHours));
	}
}

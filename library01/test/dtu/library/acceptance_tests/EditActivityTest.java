package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import controllerInterface.ControllerActivity;
import controllerInterface.ControllerApplication;
import controllerInterface.ControllerProject;
import io.cucumber.java.en.*;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;

public class EditActivityTest {

	
	private ProjectManager projectManager;
	private ActivityManager activityManager;
	private ErrorMessageHolder errorMessageHolder;
	private String name;

	private Project project;
	private Activity currentActivity;
	private String startDate,endDate;
	Activity newActivity;

	public EditActivityTest(View view,ProjectManager projectManager, ActivityManager activityManager ,ErrorMessageHolder errorMessageHolder) {
		
		this.projectManager = projectManager;
		this.activityManager = activityManager;
		this.errorMessageHolder = errorMessageHolder;
	}
	@Given("a project with id {string} has and activity {string}")
	public void aProjectWithIdHasAndActivity(String id, String name) throws OperationNotAllowedException {
		project = projectManager.createProject("TESTNAME");
		project.setId(id);
		projectManager.addProject(project);
		activityManager.addActivity(project, name);
		assertTrue(project.hasActivity(name));
	}
	@When("the user inputs start date {string} and end date {string}")
	public void theUserInputsStartDateAndEndDate(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		assertTrue((activityManager.verifyDateFormat(startDate) && activityManager.verifyDateFormat(endDate)));
	}

	@Then("set the start to {string} and end date to {string} for the activity {string}")
	public void setTheStartToAndEndDateToForTheActivity(String startDate, String endDate, String activity) throws OperationNotAllowedException {
		project = projectManager.createProject("TESTNAME");
		project.setId("030303");
		projectManager.addProject(project);
		activityManager.addActivity(project, activity);
		currentActivity = activityManager.getActivity(project, activity);

		activityManager.setActivityStart(project,currentActivity, startDate);
		activityManager.setActivityEnd(project,currentActivity, endDate);
		assertEquals(activityManager.stringToDate(startDate), currentActivity.getStartDate());
		assertEquals(activityManager.stringToDate(endDate), currentActivity.getEndDate());


	}
	@Then("an error message {string} is given for the activity")
	public void anErrorMessageIsGivenForTheActivity(String string) throws OperationNotAllowedException {
		project = projectManager.createProject("TESTNAME");
		project.setId("030303");
		projectManager.addProject(project);
		activityManager.addActivity(project, "testActivity");
		currentActivity = activityManager.getActivity(project, "testActivity");

		try {
			activityManager.setActivityStart(project, currentActivity,startDate);
			activityManager.setActivityEnd(project,currentActivity ,endDate);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
			assertEquals(errorMessageHolder.getErrorMessage(), string);
		}
	}

	@When("the user chooses the activity {string}")
	public void theUserChoosesTheActivity(String activityName) throws OperationNotAllowedException {
		project = projectManager.createProject("TESTNAME");
		project.setId("030303");
		projectManager.addProject(project);
		activityManager.addActivity(project, activityName);
		currentActivity = activityManager.getActivity(project, activityName);

		assertEquals(currentActivity.getName(), activityName);
	}

	@When("the user changes the activity name to {string}")
	public void theUserChangesTheActivityNameTo(String activityName) {
		assertTrue(activityManager.verifyLegalActivityName(project,activityName));

	}

	@Then("the activitys name is changed to {string}")
	public void theActivitysNameIsChangedTo(String newActivityName) {
		try{
			activityManager.changeActivityName(project,currentActivity,newActivityName);
			assertEquals(currentActivity.getName(), newActivityName);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the user changes the activity description to {string}")
	public void theUserChangesTheActivityDescriptionTo(String description) {
		assertTrue(activityManager.legalDescription(description));
	}

	@Then("the activitys description is changed to {string}")
	public void theActivitysDescriptionIsChangedTo(String newDescription) {
		try{
			activityManager.changeActivityDescription(project,currentActivity,newDescription);
			assertEquals(currentActivity.getDescription(), newDescription);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@When("the user changes its budgetted time to {string}")
	public void theUserChangesItsBudgettedTimeTo(String budgettedHours) {

		assertTrue(activityManager.stringIsInteger(budgettedHours));
	}
	@Then("the activity's budgetted time is set to {string}")
	public void theActivitySBudgettedTimeIsSetTo(String budgettedHours) {
		activityManager.setBudgettedHours(currentActivity,budgettedHours);
		assertTrue(currentActivity.getBudgettedHoursTotal() == Double.parseDouble(budgettedHours));
	}
	
}

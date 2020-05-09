package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import controllerInterface.ControllerProject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;
import projectManagerObjects.Worker;

public class TimeTest {

	private View view;
	private ControllerProject controllerProject;
	private ErrorMessageHolder errorMessageHolder;

	private Project project;
	private Activity activity;
	private String ID;
	private List<Project> projects;

	private ProjectManager projectManager;
	private ActivityManager activityManager;
	private WorkerManager workerManager;
	public TimeTest(View view,ProjectManager projectManager,ActivityManager activityManager, WorkerManager workerManager, ErrorMessageHolder errorMessageHolder){

		this.projectManager = projectManager;
		this.activityManager = activityManager;
		this.workerManager = workerManager;
		this.errorMessageHolder  = errorMessageHolder;
	}

	@Given("a project with  id {string} exists")
	public void aProjectWithIdExists(String projectId) throws OperationNotAllowedException {
		project = projectManager.createProject("testName");
		project.setId(projectId);
		projectManager.addProject(project);
		assertTrue(projectManager.hasID(projectId));
	}


	@Given("the activity {string} exists in the project {string}")
	public void theActivityExistsInTheProject(String activityName, String projectID) throws OperationNotAllowedException {
		project = projectManager.getProject(projectID);
		activityManager.addActivity(project,activityName);
		activity = activityManager.getActivity(project,activityName);

	}

	@Given("the activity {string} exists in the project")
	public void theActivityExistsInTheProject(String activityName) throws OperationNotAllowedException {
		activityManager.addActivity(project,activityName);
		activity = activityManager.getActivity(project,activityName);

		assertTrue(activityManager.activityExists(project, activityName));
	}
	@Given("the user with id {string} is assigned to the activity {string}")
	public void theUserWithIdIsAssignedToTheActivity(String userId, String activityName) {
		activity = activityManager.getActivity(project,activityName);
		workerManager.createWorker("Mike", "Oxlong");
		Worker mike = workerManager.getWorker(userId);
		workerManager.assignWorker(activity, mike);
		assertTrue(activity.hasWorker(mike));
	}

	@Then("the user {string} logs {string} hours on day {string} in the activity {string}")
	public void theUserLogsHoursOnDayInTheActivity(String workerID, String time, String date, String activityName) {
		activity = activityManager.getActivity(project, activityName);
		activityManager.addShift(activity,workerID,date,time);
	}
	
	@Then("the time {string} {string} {string} can be found in the activity {string}")
	public void theTimeCanBeFoundInTheActivity(String workerID, String date, String time, String activityName) {
		activity = activityManager.getActivity(project, activityName);
		assertTrue(activityManager.hasShift(activity,workerID,date));
	}



	
	@Then("the user {string} logs absence in the form of {string} in the time period {string} to {string}")
	public void theUserLogsAbsenceInTheFormOfInTheTimePeriodTo(String userId, String absenceType, String startAbsence, String endAbsence) {
		workerManager.assignAbsence(userId,startAbsence,endAbsence);
	}
	


	@Given("the activity {string} has a total of {string} hours budgetted")
	public void theActivityHasATotalOfHoursBudgetted(String activityName, String budgettedHours) {
		activity = project.getActivity(activityName);
		activity.setBudgettedHours(budgettedHours);
		assertTrue(activity.getBudgettedHoursTotal() == Double.parseDouble(budgettedHours));

	}

	@Then("the activity has {string} hours left")
	public void theActivityHasHoursLeft(String budgettedHoursLeft) {
		assertTrue(activity.getBudgettedHoursLeft() == Double.parseDouble(budgettedHoursLeft));
	}
	
	@Then("the time {string} {string} {string} can not be found in the activity {string}")
	public void theTimeCanNotBeFoundInTheActivity(String workerID, String date, String time, String activityName) {
		activity = activityManager.getActivity(project, activityName);
		assertFalse(activityManager.hasShift(activity,workerID,date));
	}
	
	@Then("the user {string} removes the shift {string} in the activity {string}")
	public void theUserRemovesTheShiftInTheActivity(String workerID, String date, String activityName) {
		activity = project.getActivity(activityName);
		LocalDate dateConverted = activity.stringToDate(date);
		activity.removeShift(workerID, dateConverted);
	}


}

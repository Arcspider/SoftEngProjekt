package dtu.library.acceptance_tests;

import static org.junit.Assert.assertTrue;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.View;
import projectManagerObjects.Worker;

public class TimeTest {

	private Project project;
	private Activity activity;
	private ProjectManager modelProject;
	private ActivityManager modelActivity;
	private WorkerManager modelWorker;
	public TimeTest(View view,ProjectManager modelProject,ActivityManager modelActivity, WorkerManager modelWorker, ErrorMessageHolder errorMessageHolder){

		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.modelWorker = modelWorker;
	}

	@Given("a project with  id {string} exists")
	public void aProjectWithIdExists(String projectId) throws OperationNotAllowedException {
		project = modelProject.createProject("testName");
		project.setId(projectId);
		modelProject.addProject(project);
		assertTrue(modelProject.hasID(projectId));
	}


	@Given("the activity {string} exists in the project {string}")
	public void theActivityExistsInTheProject(String activityName, String projectID) throws OperationNotAllowedException {
		project = modelProject.getProject(projectID);
		modelActivity.addActivity(project,activityName);
		activity = project.getActivity(activityName);

	}

	@Given("the activity {string} exists in the project")
	public void theActivityExistsInTheProject(String activityName) throws OperationNotAllowedException {
		modelActivity.addActivity(project,activityName);
		activity = project.getActivity(activityName);

		assertTrue(modelActivity.activityExists(project, activityName));
	}
	@Given("the user with id {string} is assigned to the activity {string}")
	public void theUserWithIdIsAssignedToTheActivity(String userId, String activityName) {
		activity = modelActivity.getActivity(project, activityName);
		modelWorker.createWorker("Mike", "Oxlong");
		Worker mike = modelWorker.getWorker(userId);
		modelWorker.assignWorker(activity, mike);
		assertTrue(activity.hasWorker(mike));
	}

	@Then("the user logs {string} hours on day {string}")
	public void theUserLogsHoursOnDay(String hours, String day) {
		assertTrue(modelActivity.allowedHours(hours) && modelActivity.verifyFormatddmmyyyy(day));
	}
	@Then("the time {string} {string} {string} can be found in the activity {string}")
	public void theTimeCanBeFoundInTheActivity(String workerID, String date, String time, String activityName) {
		activity = modelActivity.getActivity(project, activityName);
		modelActivity.addShift(activity,workerID,date,time);
		assertTrue(modelActivity.hasShift(activity,workerID,date));
	}



	@Then("the user again logs {string} hours on day {string}")
	public void theUserAgainLogsHoursOnDay(String hours, String day) {
		assertTrue(modelActivity.allowedHours(hours) && modelActivity.verifyFormatddmmyyyy(day));
	}
	@Then("the user {string} logs absence in the form of {string} in the time period {string} to {string}")
	public void theUserLogsAbsenceInTheFormOfInTheTimePeriodTo(String userId, String absenceType, String startAbsence, String endAbsence) {
		modelWorker.assignAbsence(userId,startAbsence,endAbsence);
	}
}

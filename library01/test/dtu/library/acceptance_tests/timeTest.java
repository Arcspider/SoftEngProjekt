package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import dtu.library.app.Activity;
import dtu.library.app.Model;
import dtu.library.app.ModelActivity;
import dtu.library.app.ModelProject;
import dtu.library.app.ModelWorker;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.Worker;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class timeTest {

	private View view;
	private ControllerProject controllerProject;
	private ErrorMessageHolder errorMessageHolder;

	private Project project;
	private Activity activity;
	private String ID;
	private List<Project> projects;

	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ModelWorker modelWorker;
	public timeTest(View view,ModelProject modelProject,ModelActivity modelActivity, ModelWorker modelWorker, ErrorMessageHolder errorMessageHolder){

		this.modelProject = modelProject;
		this.modelActivity = modelActivity;
		this.modelWorker = modelWorker;
		this.errorMessageHolder  = errorMessageHolder;
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
		modelWorker.assignAbsence(userId,absenceType,startAbsence,endAbsence);
	}
}

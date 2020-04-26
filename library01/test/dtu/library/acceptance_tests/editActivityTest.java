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

public class editActivityTest {

	private View view;
	private Model model;
	private Controller controller;
	private ControllerProject controllerProject;
	private ControllerActivity controllerActivity;
	private ErrorMessageHolder errorMessageHolder;
	private String name;

	private Project project;
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
	    project = controller.createProject(name);
	    project.setId(id);
	    controller.addProject(project);
	    controllerActivity.addActivity(name, project);
	}
	@Given("project was start date {string} and end date {string}")
	public void projectWasStartDateAndEndDate(String startDate, String endDate) {
		controllerProject.setProjectStart(project, startDate);
    	controllerProject.setProjectEnd(project, endDate);

	}
	@When("the user inputs start date {string} and end date {string}")
	public void theUserInputsStartDateAndEndDate(String startDate, String string2) {

	}
}

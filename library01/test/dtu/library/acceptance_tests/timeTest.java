package dtu.library.acceptance_tests;

import dtu.library.app.Model;
import dtu.library.app.ModelActivity;
import dtu.library.app.ModelProject;
import dtu.library.app.ModelWorker;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

import java.util.List;

public class timeTest {

    private View view;
    private ControllerProject controllerProject;
    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    private String ID;
    private List<Project> projects;
	private Model model;
	private ModelProject modelProject;
	private ModelActivity modelActivity;
	private ModelWorker modelWorker;
	public timeTest(View view,Model model,ModelProject modelProject,ModelActivity modelActivity, ModelWorker modelWorker, ErrorMessageHolder errorMessageHolder){
    	this.model = model;
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
    @Given("the activity {string} exists in the project")
    public void theActivityExistsInTheProject(String activityName) throws OperationNotAllowedException {
    	modelActivity.addActivity(project,activityName);
    	
    	assertTrue(modelActivity.activityExists(project, activityName));
    }
    @Given("the user with id {string} is assigned to the activity")
    public void theUserWithIdIsAssignedToTheActivity(String string) {
    	
    }
}

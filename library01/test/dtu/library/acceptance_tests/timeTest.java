package dtu.library.acceptance_tests;

import dtu.library.app.Model;
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
	
	public timeTest(View view,Model model,ErrorMessageHolder errorMessageHolder){
    	this.model = model;
    	this.errorMessageHolder  = errorMessageHolder;
    }

    @Given("a project with  id {string} exists")
    public void aProjectWithIdExists(String projectId) throws OperationNotAllowedException {
    	project = model.createProject("testName");
    	project.setId(projectId);
        model.addProject(project);
        
        assertTrue(model.hasID(projectId));
    }
    @Given("the activity {string} exists in the project")
    public void theActivityExistsInTheProject(String activityName) throws OperationNotAllowedException {
    	model.addActivity(project,activityName);
    	
    	assertTrue(model.activityExists(project, activityName));
    }
    @Given("the user with id {string} is assigned to the activity")
    public void theUserWithIdIsAssignedToTheActivity(String string) {
    	
    }
}

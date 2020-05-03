package dtu.library.acceptance_tests;

import dtu.library.app.Model;
import dtu.library.app.ModelProject;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerApplication;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoveProjectTest {

    private View view;
    private Model model;
    private	ModelProject modelProject;
    
    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    private String ID;
    private List<Project> projects;

    public RemoveProjectTest(View view,Model model,ModelProject modelProject, ErrorMessageHolder errorMessageHolder) {
        this.view = view;
        this.model = model;
        this.modelProject = modelProject;
        this.errorMessageHolder = errorMessageHolder;
        
    }

    @Given("the user deletes a project {string}")
    public void theUserDeletesAProject(String name) throws OperationNotAllowedException {
        project = modelProject.createProject(name);
        modelProject.addProject(project);
    }

    @Given("the project {string} exists")
    public void theProjectExists(String name) {
        assertTrue(modelProject.hasID(project.getId()));
    }

    @Then("the project is deleted")
    public void theProjectIsDeleted() throws OperationNotAllowedException {
        modelProject.removeProject(project);
    }

    @Then("the project {string} no longer exists")
    public void theProjectNoLongerExists(String id) {
        assertFalse(modelProject.hasID(id));
    }
    @Given("that the project {string} doesn't exist")
    public void thatTheProjectDoesnTExist(String id) {
    	  project = new Project("Beta", id);
    	 assertFalse(modelProject.hasID(id));
    }

    @Given("the user tries to delete the project")
    public void theUserTriesToDeleteTheProject() {
    	 try {
    		 modelProject.removeProject(project);
         } catch (OperationNotAllowedException e) {
             errorMessageHolder.setErrorMessage(e.getMessage());
         }
    }

    @Then("the error message {string} is shown")
    public void theErrorMessageIsShown(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    	 assertEquals(errorMessage
                 , this.errorMessageHolder.getErrorMessage());
     
    }



   
}

package dtu.library.acceptance_tests;

import dtu.library.app.*;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class ProjectTest {

    private View view;
    private ControllerProject controllerProject;
    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    private String ID;
    private List<Project> projects;
    

    public ProjectTest(View view, ErrorMessageHolder errorMessageHolder, ControllerProject controllerProject) {
        this.view = view;
        this.errorMessageHolder = errorMessageHolder;
        this.controllerProject = controllerProject;
    }

    @Given("a user creates a project with name {string}")
    public void aUserCreatesAProjectWithName(String name) throws OperationNotAllowedException {
        ID = controllerProject.generateID();
        project = controllerProject.createProject(name,ID);
        
    }

    @Given("there is no project with the ID from the project")
    public void thereIsNoProjectWithTheIDFromTheProject() {
        assertFalse(controllerProject.exists(ID));
    }

    @When("a project is created")
    public void aProjectIsCreated() throws OperationNotAllowedException {
        controllerProject.addProject(project);
    }

    @Then("the project with the ID is contained in the list")
    public void theProjectWithTheIDIsContainedInTheList() {
        assertTrue(controllerProject.exists(ID));
        
    }

    @Given("a user creates another project with name {string}")
    public void aUserCreatesAnotherProjectWithName(String name) throws OperationNotAllowedException {
    	  ID = controllerProject.generateID();
          project = controllerProject.createProject(name,ID);
          
    }

    @Then("the project is not created")
    public void theProjectIsNotCreated() {
        try {
            assertFalse(controllerProject.checkName(project.getName()));
        } catch (OperationNotAllowedException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the user receives an error message {string}")
    public void theUserReceivesAnErrorMessage(String errorMessage) {
        assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
    }
    
    
    @When("the user chooses the project with id of project Alpha.")
    public void theUserEditsProjectDescription() throws OperationNotAllowedException {
  	  // Der dannes et nyt projekt siden projekt dataen ikke overlever hop mellem filer.
      ID = controllerProject.generateID();
      project = controllerProject.createProject("ALPHA",ID);
      controllerProject.addProject(project);

    }
    @And ("the user enters description {string}")
    public void theUserEditsProjectName(String newDescription) throws OperationNotAllowedException {
    	
    	assertTrue(controllerProject.editProjectDescription(ID, newDescription));
    }
    
    @Then("the projects description is overwritten with {string}")
    public void theUserhasEditedProjectName(String newDescription) throws OperationNotAllowedException {
    	assertTrue(project.getDescription().equals(newDescription));
    }
    
    @When("the user chooses the project with id {string}")
    public void theUserChoosesTheProjectWithId(String string) throws OperationNotAllowedException {
    	 ID = controllerProject.generateID();
         project = controllerProject.createProject("ALPHA",ID);
         controllerProject.addProject(project);
    	
    }
    @When("the user changes the name from {string} to {string}")
    public void theUserChangesTheNameFromTo(String string, String string2) {
        controllerProject.editProjectName(project.getId(), string2);
    }
    @Then("the projects Name is changed to {string}")
    public void theProjectsNameIsChangedTo(String name) throws OperationNotAllowedException {
    	assertTrue(project.getName().equals(name));
    }

}

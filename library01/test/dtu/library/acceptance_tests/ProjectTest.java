package dtu.library.acceptance_tests;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;

import org.junit.Assert;

import applicationManagerInterface.ProjectManager;
import controllerInterface.ControllerProject;

import java.util.List;

import static org.junit.Assert.*;

public class ProjectTest {


    private ProjectManager projectManager;

    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    private String ID;
    private List<Project> projects;


    public ProjectTest(ErrorMessageHolder errorMessageHolder, ProjectManager projectManager) {

        this.projectManager = projectManager;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a user creates a project with name {string}")
    public void aUserCreatesAProjectWithName(String name) throws OperationNotAllowedException {
        project = projectManager.createProject(name);
        projectManager.addProject(project);
    }

    @Then("the project with the ID is contained in the list")
    public void theProjectWithTheIDIsContainedInTheList() throws OperationNotAllowedException {
        assertTrue(projectManager.hasID(project.getId()));

    }

    @Given("a user creates another project with name {string}")
    public void aUserCreatesAnotherProjectWithName(String name) throws OperationNotAllowedException {
          project = projectManager.createProject(name);
    }

    @Then("the project is not created")
    public void theProjectIsNotCreated() {
        try {
            assertFalse(projectManager.checkName(project.getName()));
        } catch (OperationNotAllowedException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the user receives an error message {string}")
    public void theUserReceivesAnErrorMessage(String errorMessage) {
        assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
    }


    @When("the user chooses the project with id of project {string}.")
    public void theUserEditsProjectDescription(String name) throws OperationNotAllowedException {
  	  // Der dannes et nyt projekt siden projekt dataen ikke overlever hop mellem filer.
      project = projectManager.createProject(name);
      projectManager.addProject(project);

    }

    @And ("the user enters description {string}")
    public void theUserEditsProjectName(String newDescription) throws OperationNotAllowedException {
    	assertTrue(projectManager.editProjectDescription(project, newDescription));
    }

    @Then("the projects description is overwritten with {string}")
    public void theUserhasEditedProjectName(String newDescription) throws OperationNotAllowedException {
        assertEquals(project.getDescription(), newDescription);
    }

    @When("the user chooses the project {string} with the id {string}")
    public void theUserChoosesTheProjectWithTheId(String name, String id) throws OperationNotAllowedException {
         project = projectManager.createProject(name);
         project.setId(id);
         projectManager.addProject(project);

    }

	@When("the user changes the name to {string}")
	public void theUserChangesTheNameTo(String string) {
		projectManager.editProjectName(project, string);
	}

    @Then("the projects Name is changed to {string}")
    public void theProjectsNameIsChangedTo(String name) throws OperationNotAllowedException {
        assertEquals(project.getName(), name);
    }

    @When("the user enters the start and end dates {string} and {string}")
    public void theUserEntersTheStartAndEndDatesAnd(String startDate, String endDate) {
        assertTrue((projectManager.verifyDateFormat(startDate) && projectManager.verifyDateFormat(endDate)));
    }

    @Then("the projects start and end dates are changed to {string} and {string}")
    public void theProjectsStartAndEndDatesAreChangedToAnd(String startDate, String endDate) {
    	projectManager.setProjectStart(project, startDate);
    	projectManager.setProjectEnd(project, endDate);
        assertEquals(project.getStartDate().toString(),projectManager.getProjectStart(project).toString());
        assertEquals(project.getEndDate().toString(),projectManager.getProjectEnd(project).toString());
    }
}

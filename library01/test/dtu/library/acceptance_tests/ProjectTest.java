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
    public void aUserCreatesAProjectWithName(String name) {
        controllerProject.runCommand("Create Project", name);
        project = controllerProject.getProject();
        ID = project.getId();
    }

    @Given("there is no project with the ID from the project")
    public void thereIsNoProjectWithTheIDFromTheProject() {
        assertFalse(controllerProject.exists(ID));
    }

    @When("a project is created")
    public void aProjectIsCreated() {
        controllerProject.addProject(project);
    }

    @Then("the project with the ID is contained in the list")
    public void theProjectWithTheIDIsContainedInTheList() {
        assertTrue(controllerProject.exists(ID));
    }

    @Given("a user creates another project with name {string}")
    public void aUserCreatesAnotherProjectWithName(String name) {
        controllerProject.runCommand("Create Project", name);
        project = controllerProject.getProject();
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

}

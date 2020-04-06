package dtu.library.acceptance_tests;

import dtu.library.app.*;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProjectTest {

    private View view;
    private ControllerProject controllerProject;
    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    private String ID;
    private List<Project> projects;

    public ProjectTest(View view, ErrorMessageHolder errorMessageHolder) {
        this.view = view;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a user creates a project with name {string}")
    public void aUserCreatesAProjectWithName(String name) {
        controllerProject.runCommand("Create Project", name);
        project = controllerProject.getProject();
        ID = project.getId();
    }

    @Given("there is no project with the {string} from the project")
    public void thereIsNoProjectWithTheFromTheProject(String ID) {
        assertFalse(controllerProject.exists(ID));
    }

    @When("a project is created")
    public void aProjectIsCreatedWithName(Project project) {
        controllerProject.addProject(project);
    }

    @Then("the project with the {string} is contained in the list")
    public void theProjectWithTheIsContainedInTheList(String ID) {
        assertTrue(controllerProject.exists(ID));
    }


//
//
//    @Given("a user creates another project with name {string}")
//    public void aUserCreatesAnotherProjectWithName(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Given("there is a project with the name {string}")
//    public void thereIsAProjectWithTheName(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//    @Then("the project is not created")
//    public void theProjectIsNotCreated() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//    @Then("the user receives the error message {string}")
//    public void theUserReceivesTheErrorMessage(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

}

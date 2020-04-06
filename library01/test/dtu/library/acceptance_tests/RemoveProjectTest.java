package dtu.library.acceptance_tests;

import dtu.library.app.Project;
import dtu.library.app.View;
import dtu.library.app.controllerInterface.ControllerProject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemoveProjectTest {

    private View view;
    private ControllerProject controllerProject;
    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    private String ID;
    private List<Project> projects;

    public RemoveProjectTest(View view, ErrorMessageHolder errorMessageHolder, ControllerProject controllerProject) {
        this.view = view;
        this.errorMessageHolder = errorMessageHolder;
        this.controllerProject = controllerProject;
    }

    @Given("the user deletes a project {string}")
    public void theUserDeletesAProject(String ID) {
        project = new Project("Beta", ID);
        controllerProject.addProject(project);
    }

    @Given("the project {string} exists")
    public void theProjectExists(String ID) {
        assertTrue(controllerProject.exists(ID));
    }

    @Then("the project is deleted")
    public void theProjectIsDeleted() {
        controllerProject.removeProject(project);
    }

    @Then("the project {string} no longer exists")
    public void theProjectNoLongerExists(String ID) {
        assertFalse(controllerProject.exists(ID));
    }
//
//    @Given("the user deletes project {string}")
//    public void theUserDeletesProject(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Given("the project {string} doesn't exist")
//    public void theProjectDoesnTExist(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("the error message {string} is shown")
//    public void theErrorMessageIsShown(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
}

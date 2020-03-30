package dtu.library.acceptance_tests;

import dtu.library.app.Book;
import dtu.library.app.LibraryApp;
import dtu.library.app.Project;
import dtu.library.app.View;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.List;

public class ProjectTest {

    private View view;
    private ErrorMessageHolder errorMessageHolder;

    private Project project;
    private List<Project> projects;

    public ProjectTest(View view, ErrorMessageHolder errorMessageHolder) {
        this.view = view;
        this.errorMessageHolder = errorMessageHolder;
    }

    @Given("a user creates a project with name {string}")
    public void aUserCreatesAProjectWithName(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there is no project with the ID {string}")
    public void thereIsNoProjectWithTheName(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("a project is created with name {string}")
    public void aProjectIsCreatedWithName(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
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

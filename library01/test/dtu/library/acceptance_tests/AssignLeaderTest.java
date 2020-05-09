package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import io.cucumber.java.en.*;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.Worker;

public class AssignLeaderTest {

    private ProjectManager projectManager;
    private WorkerManager workerManager;
    private ErrorMessageHolder errorMessageHolder;
    private Worker worker;

    private Project project;
 
    public AssignLeaderTest(ProjectManager  projectManager, WorkerManager workerManager,ErrorMessageHolder errorMessageHolder) throws OperationNotAllowedException {
    	this.projectManager = projectManager;
    	this.workerManager = workerManager;
    	this.errorMessageHolder = errorMessageHolder;
    	project = projectManager.createProject("Tom");
    	projectManager.addProject(project);
    	worker = workerManager.createWorker("Bob", "Gnarty");
    }

    @Given("that a project with id {string} does not have a leader")
    public void thatAProjectWithIdDoesNotHaveALeader(String string) {
    	project.setId(string);
        assertFalse(project.hasLeader());
    }

    @When("the user adds the worker with id {string} as leader")
    public void theUserAddsTheWorkerWithIdAsLeader(String string) throws OperationNotAllowedException {
    	worker.setID(string);
    	assertTrue(workerManager.workerHasID(string));
        assertTrue(projectManager.setLeader(project,worker));
    }

    @Then("the worker with id {string} as leader for project {string}")
    public void theWorkerWithIdAsLeaderForProject(String string, String string2) {
    	assertEquals(worker, project.getLeader());
    }

    @Given("that a worker with id {string} is a leader for project {string}")
    public void thatAWorkerWithIdIsALeaderForProject(String workerID, String projectID) throws OperationNotAllowedException {
    	worker = workerManager.createWorker(worker.getFirstName(), worker.getLastName());
    	worker.setID(workerID);
    	project.setId(projectID);
    	assertTrue(projectManager.setLeader(project, worker));
    	assertEquals(worker, project.getLeader());
    }

    @When("the user adds the worker with id {string} as leader to the project")
    public void theUserAddsTheWorkerWithIdAsLeaderToTheProject(String string) {
    	try {
    	assertFalse(projectManager.setLeader(project, worker));
        } catch (OperationNotAllowedException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("an error message {string} is written")
    public void anErrorMessageIsWritten(String string) {
    	assertEquals(string, this.errorMessageHolder.getErrorMessage());
    }

    @Given("that a worker with id {string} is leader for project {string}")
    public void thatAWorkerWithIdIsLeaderForProject(String workerID, String projectID) throws OperationNotAllowedException {
    	worker = workerManager.createWorker("Mike", "Hunt");
    	worker.setID(workerID);
    	project = projectManager.createProject("Pro");
    	project.setId(projectID);
    	projectManager.addProject(project);
    	assertTrue(projectManager.setLeader(project, worker));
    }


    @When("the user adds the worker with id {string} as a leader for the project")
    public void theUserAddsTheWorkerWithIdAsALeaderForTheProject(String string) {
    	worker = workerManager.createWorker("Tom", "Bob");
    	worker.setID(string);
    	try {
    	assertFalse(projectManager.setLeader(project, worker));
        } catch (OperationNotAllowedException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("an error message {string} is witten")
    public void anErrorMessageIsWitten(String string) {
    	assertEquals(string, this.errorMessageHolder.getErrorMessage());
    }

}

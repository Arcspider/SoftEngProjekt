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

    private ProjectManager modelProject;
    private WorkerManager modelWorker;
    private ErrorMessageHolder errorMessageHolder;
    private Worker worker;

    private Project project;
 
    public AssignLeaderTest(ProjectManager  modelProject, WorkerManager modelWorker,ErrorMessageHolder errorMessageHolder) throws OperationNotAllowedException {
    	this.modelProject = modelProject;
    	this.modelWorker = modelWorker;
    	this.errorMessageHolder = errorMessageHolder;
    	project = modelProject.createProject("Tom");
    	modelProject.addProject(project);
    	worker = modelWorker.createWorker("Bob", "Gnarty");
    }

    @Given("that a project with id {string} does not have a leader")
    public void thatAProjectWithIdDoesNotHaveALeader(String string) {
    	project.setId(string);
        assertFalse(project.hasLeader());
    }

    @When("the user adds the worker with id {string} as leader")
    public void theUserAddsTheWorkerWithIdAsLeader(String string) throws OperationNotAllowedException {
    	worker.setID(string);
    	assertTrue(modelWorker.workerHasID(string));
        assertTrue(modelProject.setLeader(project,worker));
    }

    @Then("the worker with id {string} as leader for project {string}")
    public void theWorkerWithIdAsLeaderForProject(String string, String string2) {
    	assertEquals(worker, project.getLeader());
    }

    @Given("that a worker with id {string} is a leader for project {string}")
    public void thatAWorkerWithIdIsALeaderForProject(String workerID, String projectID) throws OperationNotAllowedException {
    	worker = modelWorker.createWorker(worker.getFirstName(), worker.getLastName());
    	worker.setID(workerID);
    	project.setId(projectID);
    	assertTrue(modelProject.setLeader(project, worker));
    	assertEquals(worker, project.getLeader());
    }

    @When("the user adds the worker with id {string} as leader to the project")
    public void theUserAddsTheWorkerWithIdAsLeaderToTheProject(String string) {
    	try {
    	assertFalse(modelProject.setLeader(project, worker));
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
    	worker = modelWorker.createWorker("Mike", "Hunt");
    	worker.setID(workerID);
    	project = modelProject.createProject("Pro");
    	project.setId(projectID);
    	modelProject.addProject(project);
    	assertTrue(modelProject.setLeader(project, worker));
    }


    @When("the user adds the worker with id {string} as a leader for the project")
    public void theUserAddsTheWorkerWithIdAsALeaderForTheProject(String string) {
    	worker = modelWorker.createWorker("Tom", "Bob");
    	worker.setID(string);
    	try {
    	assertFalse(modelProject.setLeader(project, worker));
        } catch (OperationNotAllowedException e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
 
    @Then("an error message {string} is witten")
    public void anErrorMessageIsWitten(String string) {
    	assertEquals(string, this.errorMessageHolder.getErrorMessage());
    }

}

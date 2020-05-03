package dtu.library.acceptance_tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import dtu.library.app.Model;
//import dtu.library.app.OperationNotAllowedException;
//import dtu.library.app.Project;
//import dtu.library.app.Worker;
//import io.cucumber.java.en.*;
//
//public class AssignLeaderTest {
//
//    private Model model;
//    private ErrorMessageHolder errorMessageHolder;
//    private Worker worker;
//
//    private Project project;
//
//    public AssignLeaderTest(Model model, ErrorMessageHolder errorMessageHolder) {
//    	this.model = model;
//    	this.errorMessageHolder = errorMessageHolder;
//    	project = new Project("Tom", "coolID");
//    	worker = new Worker("Bob", "Gnarly", "420", model);
//
//    }
//
//    @Given("that a project with id {string} does not have a leader")
//    public void thatAProjectWithIdDoesNotHaveALeader(String string) {
//    	project.setId(string);
//        assertFalse(project.hasLeader());
//    }
//
//    @When("the user adds the worker with id {string} as leader")
//    public void theUserAddsTheWorkerWithIdAsLeader(String string) throws OperationNotAllowedException {
//    	worker = model.createWorker(worker.getFirstName(), worker.getLastName());
//    	worker.setID(string);
//        assertTrue(project.setLeader(worker));
//    }
//
//    @Then("the worker with id {string} as leader for project {string}")
//    public void theWorkerWithIdAsLeaderForProject(String string, String string2) {
//    	assertEquals(worker, project.getLeader());
//    }
//
//    @Given("that a worker with id {string} is a leader for project {string}")
//    public void thatAWorkerWithIdIsALeaderForProject(String string, String string2) throws OperationNotAllowedException {
//    	worker = model.createWorker(worker.getFirstName(), worker.getLastName());
//    	worker.setID(string);
//    	project.setId(string2);
//    	project.setLeader(worker);
//    	assertEquals(worker, project.getLeader());
//    }
//
//    @When("the user adds the worker with id {string} as leader to the project")
//    public void theUserAddsTheWorkerWithIdAsLeaderToTheProject(String string) {
//    	try {
//    	assertFalse(project.setLeader(worker));
//        } catch (OperationNotAllowedException e) {
//            errorMessageHolder.setErrorMessage(e.getMessage());
//        }
//    }
//
//    @Then("an error message {string} is written")
//    public void anErrorMessageIsWritten(String string) {
//    	assertEquals(string, this.errorMessageHolder.getErrorMessage());
//    }
//
//    @Given("that a worker with id {string} is leader for project {string}")
//    public void thatAWorkerWithIdIsLeaderForProject(String string, String string2) throws OperationNotAllowedException {
//    	Worker Mike = new Worker("Mike", "Hunt", string, model);
//    	Mike = model.createWorker(Mike.getFirstName(), Mike.getLastName());
//    	Mike.setID(string);
//    	project = new Project("Pro", string2);
//    	assertTrue(project.setLeader(Mike));
//
//    }
//
//
//    @When("the user adds the worker with id {string} as a leader for the project")
//    public void theUserAddsTheWorkerWithIdAsALeaderForTheProject(String string) {
//    	worker.setID(string);
//    	try {
//    	assertFalse(project.setLeader(worker));
//        } catch (OperationNotAllowedException e) {
//            errorMessageHolder.setErrorMessage(e.getMessage());
//        }
//    }
//
//    @Then("an error message {string} is witten")
//    public void anErrorMessageIsWitten(String string) {
//    	assertEquals(string, this.errorMessageHolder.getErrorMessage());
//    }
//
//}

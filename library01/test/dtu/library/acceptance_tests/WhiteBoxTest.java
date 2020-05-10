package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Test;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import applicationManagerInterface.WorkerManager;
import projectManagerObjects.Activity;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;
import projectManagerObjects.Worker;

public class WhiteBoxTest {
	private ActivityManager activityManager = new ActivityManager(null);
	private  ProjectManager projectManager = new ProjectManager(null);
	private WorkerManager workerManager = new WorkerManager();
	private Project project;
	private Activity activity;
	private Worker worker;
	@Test
	public void verifyDateFormatTextInputA() {
		assertFalse(activityManager.verifyDateFormat("2"));
	}

	@Test
	public void verifyDateFormatTextInputB() {
		assertFalse(activityManager.verifyDateFormat("test-2020"));
	}

	@Test
	public void verifyDateFormatTextInputC() {
		assertFalse(activityManager.verifyDateFormat("2-test"));
	}

	@Test
	public void verifyDateFormatTextInputD() {
		assertFalse(activityManager.verifyDateFormat("2-1940"));
	}

	@Test
	public void verifyDateFormatTextInputE() {
		assertFalse(activityManager.verifyDateFormat("2-2095"));
	}

	@Test
	public void verifyDateFormatTextInputF() {
		assertFalse(activityManager.verifyDateFormat("0-2020"));
	}
	
	@Test
	public void verifyDateFormatTextInputG() {
		assertFalse(activityManager.verifyDateFormat("107-2020"));
	}
	
	@Test
	public void verifyDateFormatTextInputIH() {
		assertTrue(activityManager.verifyDateFormat("20-2020"));
	}
	
	@Test
	public void setProectEndInputA() throws OperationNotAllowedException {
		project = projectManager.createProject("Tom"); 
		projectManager.setProjectEnd(project, "20-2090");
		
		assertEquals(null, project.getEndDate());
	}
	@Test
	public void setProectEndInputB() throws OperationNotAllowedException {
		project = projectManager.createProject("Tom");
		projectManager.setProjectStart(project,"20-2020" );
		projectManager.setProjectEnd(project, "18-2020");
		
		assertEquals(null, project.getEndDate());
	}
	
	@Test
	public void setProectEndInputC() throws OperationNotAllowedException {
		project = projectManager.createProject("Tom");
		projectManager.setProjectEnd(project, "18-2020");
		assertEquals(stringToDate("18-2020"), project.getEndDate());
	}
	@Test
	public void setProectEndInputD() throws OperationNotAllowedException {
		project = projectManager.createProject("Tom");
		projectManager.setProjectStart(project,"20-2020" );
		projectManager.setProjectEnd(project, "22-2020");
		assertEquals(stringToDate("22-2020"), project.getEndDate());
	}
	
	@Test
	public void allowedhoursInputA() {
		assertFalse(activityManager.allowedHours("test"));
	}
	@Test
	public void allowedhoursInputB() {
		assertFalse(activityManager.allowedHours("60"));
	}
	
	@Test
	public void allowedhoursInputC() {
		assertFalse(activityManager.allowedHours("-2"));
	}
	@Test
	public void allowedhoursInputD() {
		assertFalse(activityManager.allowedHours("0.3"));
	}
	
	@Test
	public void allowedhoursInputE() {
		assertTrue(activityManager.allowedHours("4.5"));
	}
	
	
	@Test
	public void verifyLegalShiftInputA() throws OperationNotAllowedException { 
		project = projectManager.createProject("Alfa");
		activityManager.addActivity(project, "Beta");
		activity = activityManager.getActivity(project, "Beta");
		worker = workerManager.createWorker("Tom", "Bob");
		assertFalse(activityManager.verifyLegalShift(activity, worker.getId(), "20-7-2020", "10"));
	}
	@Test
	public void verifyLegalShiftInputB() throws OperationNotAllowedException { 
		project = projectManager.createProject("Alfa");
		activityManager.addActivity(project, "Beta");
		activity = activityManager.getActivity(project, "Beta");
		worker = workerManager.createWorker("Tom", "Bob");
		workerManager.assignWorker(activity, worker);
		assertFalse(activityManager.verifyLegalShift(activity, worker.getId(), "80-10-2020", "10"));
	}
	
	@Test
	public void verifyLegalShiftInputC() throws OperationNotAllowedException { 
		project = projectManager.createProject("Alfa");
		activityManager.addActivity(project, "Beta");
		activity = activityManager.getActivity(project, "Beta");
		worker = workerManager.createWorker("Tom", "Bob");
		workerManager.assignWorker(activity, worker);
		assertFalse(activityManager.verifyLegalShift(activity, worker.getId(), "1-10-2020", "2.3"));
	}
	

	
	@Test
	public void verifyLegalShiftInputD() throws OperationNotAllowedException { 
		project = projectManager.createProject("AlfaE");
		activityManager.addActivity(project, "BetaE");
		activity = activityManager.getActivity(project, "BetaE");
		worker = workerManager.createWorker("Tom", "Bob");
		
		workerManager.assignWorker(activity, worker);
		activityManager.setBudgettedHours(activity,"10");
		
		assertFalse(activityManager.verifyLegalShift(activity, worker.getId(), "1-10-2020", "11"));
	}
	
	@Test
	public void verifyLegalShiftInputE() throws OperationNotAllowedException { 
		project = projectManager.createProject("AlfaF");
		activityManager.addActivity(project, "BetaF");
		activity = activityManager.getActivity(project, "BetaF");
		worker = workerManager.createWorker("Tom", "Bob");
		
		workerManager.assignWorker(activity, worker);
		activityManager.setBudgettedHours(activity,"30");
		
		assertTrue(activityManager.verifyLegalShift(activity, worker.getId(), "1-10-2020", "11"));
	}
	
	
	public LocalDate stringToDate(String toBeConverted) {
		String[] stringDate = toBeConverted.split("-");
		int weekInt = Integer.parseInt(stringDate[0]);
		int yearInt = Integer.parseInt(stringDate[1]);

		Calendar cldStart = Calendar.getInstance();
		cldStart.set(Calendar.YEAR, yearInt);
		cldStart.set(Calendar.WEEK_OF_YEAR, weekInt);
		return LocalDate.of(yearInt, cldStart.get(Calendar.MONTH) + 1, cldStart.get(Calendar.DATE));
	}

	
	
	
}

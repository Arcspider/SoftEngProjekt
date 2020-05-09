package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Test;

import applicationManagerInterface.ActivityManager;
import applicationManagerInterface.ProjectManager;
import projectManagerObjects.OperationNotAllowedException;
import projectManagerObjects.Project;

public class WhiteBoxTest {
	private ActivityManager activityManager = new ActivityManager(null);
	private  ProjectManager projectManager = new ProjectManager(null);
	private Project project;
	
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

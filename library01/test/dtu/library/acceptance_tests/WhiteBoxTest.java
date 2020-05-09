package dtu.library.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import applicationManagerInterface.ActivityManager;

public class WhiteBoxTest {
	ActivityManager activityManager = new ActivityManager(null);

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
		assertFalse(activityManager.verifyDateFormat("2-2090"));
	}

	@Test
	public void verifyDateFormatTextInputF() {
		assertFalse(activityManager.verifyDateFormat("0-2020"));
	}

	public void verifyDateFormatTextInputG() {
		assertFalse(activityManager.verifyDateFormat("107-2020"));
	}

	public void verifyDateFormatTextInputIH() {
		assertTrue(activityManager.verifyDateFormat("20-2020"));
	}
	
	
}

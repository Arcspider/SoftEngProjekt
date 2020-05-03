package dtu.library.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ModelActivity {
	private View view;
	private Activity thisActivity;
	private Calendar calendar;
	private DateFormat dateFormat;
	private boolean hasActivity;

	public ModelActivity(View view) {
		this.view = view;
		hasActivity = false;
		this.calendar = new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-yy");
	}

	public void setActivityStart(Project project, Activity currentActivity, String startDate)
			throws OperationNotAllowedException {
		LocalDate startActivityDate = project.getStartDate();
		LocalDate endActivityDate = project.getEndDate();
		if (verifyDateFormat(startDate)) {
			if (endActivityDate == null || startActivityDate.isBefore(endActivityDate)) {
				LocalDate newStart = stringToDate(startDate);
				project.setActivityStartDate(currentActivity, newStart);
				System.out.println(thisActivity.toString());
			} else
				throw new OperationNotAllowedException("End date is before start date");
		}
	}

	public LocalDate stringToDate(String toBeConverted) {
		String[] stringDate = toBeConverted.split("-");
		int weekInt = Integer.parseInt(stringDate[0]);
		int yearInt = Integer.parseInt(stringDate[1]);

		Calendar cldStart = Calendar.getInstance();
		cldStart.set(Calendar.YEAR, yearInt);
		cldStart.set(Calendar.WEEK_OF_YEAR, weekInt);
		LocalDate finalDate = LocalDate.of(yearInt, cldStart.get(Calendar.MONTH) + 1, cldStart.get(Calendar.DATE));
		return finalDate;

	}

	public void setActivityEnd(Project project, Activity currentActivity, String endDate)
			throws OperationNotAllowedException {
		LocalDate startActivityDate = project.getStartDate();
		LocalDate endActivityDate = project.getEndDate();
		if (verifyDateFormat(endDate)) {
			if (endActivityDate == null || startActivityDate.isBefore(endActivityDate)) {
				LocalDate newEnd = stringToDate(endDate);
				project.setActivityEndDate(currentActivity, newEnd);
				System.out.println(thisActivity.toString());
			} else
				throw new OperationNotAllowedException("Start date is after end date");
		}
	}

	public boolean verifyDateFormat(String dateToVerify) {

		String[] stringDate = dateToVerify.split("-");
		if (stringDate.length == 2 && stringIsNumeric(stringDate[0]) && stringIsNumeric(stringDate[1])) {

			int weekInt = Integer.parseInt(stringDate[0]);
			int yearInt = Integer.parseInt(stringDate[1]);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int difference = yearInt - currentYear;
			// �rstallene man arbejder indenfor er 50 �r
			if (difference >= -50 && difference <= 50) {
				if (weekInt > 0 && weekInt <= 52) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean stringIsNumeric(String test) {
		try {
			int pls = Integer.parseInt(test);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public void changeActivityDescription(Project project, Activity activity, String newDescription)
			throws OperationNotAllowedException {
		if (legalDescription(newDescription)) {
			project.changeActivityDescription(activity, newDescription);
		}
	}

	public void changeActivityName(Project project, Activity activity, String newActivityName)
			throws OperationNotAllowedException {
		if (verifyLegalActivityName(project, newActivityName)) {
			project.changeActivityName(activity, newActivityName);
		} else {
			throw new OperationNotAllowedException("Illegal name");
		}
	}

	public Activity getThisActivity() {
		return thisActivity;
	}

	public boolean verifyLegalActivityName(Project project, String activityName) {
		if (activityName == null || project.hasActivity(activityName))
			return false;
		else
			return true;
	}

	public boolean legalDescription(String description) {
		if (description == "" || description == null)
			return false;
		else
			return true;

	}

	public void setHasActivity(boolean b) {
		hasActivity = b;

	}

	public boolean hasActiviy() {
		return hasActivity;
	}

	public boolean activityExists(Project project, String name) {
		return project.hasActivity(name);
	}

	public void setThisActivity(Activity activity) {
		thisActivity = activity;
	}

	public Activity getActivity(Project project, String name) {
		return project.getActivity(name);
	}

	public boolean addActivity(Project project, String name) throws OperationNotAllowedException {
		return project.addActivity(name);
	}

}

package dtu.library.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
				System.out.println(currentActivity.toString());
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
				System.out.println(currentActivity.toString());
			} else
				throw new OperationNotAllowedException("Start date is after end date");
		}
	}

	public boolean verifyDateFormat(String dateToVerify) {

		String[] stringDate = dateToVerify.split("-");
		if (stringDate.length == 2 && stringIsInteger(stringDate[0]) && stringIsInteger(stringDate[1])) {

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

	public boolean stringIsInteger(String test) {
		try {
			int canIBeConverted = Integer.parseInt(test);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	public boolean stringIsDouble(String test) {
		try {
			double canIBeConverted = Double.parseDouble(test);
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
		return activityName != null && !project.hasActivity(activityName);
	}

	public boolean legalDescription(String description) {
		return !description.equals("");

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

	public void setBudgettedHours(Activity currentActivity, String budgettedHours) {
		currentActivity.setBudgettedHours(budgettedHours);
	}

	public boolean verifyFormatddmmyyyy(String day) {
		String[] stringDate = day.split("-");
		if (stringDate.length == 3 && stringIsInteger(stringDate[0]) && stringIsInteger(stringDate[1]) && stringIsInteger(stringDate[2])) {
			int dayInt = Integer.parseInt(stringDate[0]);
			int monthInt = Integer.parseInt(stringDate[1]);
			int yearInt = Integer.parseInt(stringDate[2]);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int difference = yearInt - currentYear;
			// aarstallene man arbejder indenfor er 50 aar
			if (difference >= -50 && difference <= 50 && monthInt <=12 && monthInt>0 && dayInt <=30 && dayInt >= 0) {
					System.out.println("legal dato ");
					return true;
			}
		}
		System.out.println("Illegal dato weewoo");
		return false;
	}
	public boolean verifyLegalShift(Activity activity,String shiftFormat) {
		String[] arrayFormat = shiftFormat.split("-");
		if(activity.hasWorkerId(arrayFormat[0]) && verifyFormatddmmyyyy(arrayFormat[1]) && allowedHours(arrayFormat[2])) {
			return true;
		}else return true;
	}
	
	
	public void addShift(Activity activity, String fullDateFormat) {
		if(verifyLegalShift(activity,fullDateFormat)) {
			//Hvis der allerede er et shift, s� bare tilf�j timerne
			if(activity.hasShiftByIdAndDate(fullDateFormat)) {
				String[] separated = fullDateFormat.split(";");
				
				ArrayList<String> shifts = activity.getShifts();
				int shiftLocation = activity.findShiftByIdAndDate(fullDateFormat);
				String tempShift = shifts.get(shiftLocation);
				String[] tempShiftSplit = tempShift.split(";");
				
				double newHours = (Double.parseDouble(separated[2]) + Double.parseDouble(tempShiftSplit[2]));
				fullDateFormat = separated[0] + separated [1] + newHours;
				activity.setShift(shiftLocation, fullDateFormat);
			}else {
				activity.addShift(fullDateFormat);
			}
		}else view.showMessage("shift wasn't added, as something was illegal ");
	}

	public boolean allowedHours(String hours) {
		if(stringIsDouble(hours)) {
			Double doubleHours = Double.parseDouble(hours);
			//Skal v�re enten heltal eller halv times intervaller.
				return (doubleHours <=16 && doubleHours >=0 && doubleHours % 0.5 ==0);				
		}
		return false;
	}

	public boolean hasShift(Activity activity, String shift) {
		return activity.hasShiftByIdAndDate(shift);
	}

	public ArrayList<String> getShifts(Activity activity){
		return activity.getShifts();
	}

	public void getWorkedShifts(Activity activity, String date){
		for(int i = 0; i < activity.getShifts().size(); i++){
			if (activity.getShifts().get(i).contains(date)){
				view.showMessage(activity.getShifts().get(i));
			}
		}
	}

}

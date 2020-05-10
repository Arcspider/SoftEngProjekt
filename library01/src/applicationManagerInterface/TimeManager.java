package applicationManagerInterface;
//Daniel(s194592), Søren(s194630) har haft ansvaret for denne klasse.
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import projectManagerObjects.Project;

public class TimeManager {

	public TimeManager() {
		new ArrayList<Project>();
		new Random();
		new GregorianCalendar();
		new SimpleDateFormat("MM-yy");
	}
	public LocalDate stringToDate(String toBeConverted) {
		String[] stringDate = toBeConverted.split("-");
		int dInt = Integer.parseInt(stringDate[0]);
		int mInt = Integer.parseInt(stringDate[1]);
		int yInt = Integer.parseInt(stringDate[2]);

		Calendar cld = Calendar.getInstance();
		cld.set(Calendar.YEAR, yInt);
		cld.set(Calendar.MONTH, mInt);
		cld.set(Calendar.DAY_OF_MONTH, dInt);

		LocalDate finalDate = LocalDate.of(cld.get(Calendar.YEAR), cld.get(Calendar.MONTH),
				cld.get(Calendar.DAY_OF_MONTH));
		return finalDate;
	}

}

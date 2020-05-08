package dtu.library.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class ModelTime {

	private ArrayList<Project> projects;
	private Project newProject;
	private Project thisProject;
	private boolean hasProject;
	private Random random;
	private DateFormat dateFormat;
	public ModelTime() {
		projects = new ArrayList<Project>();
		random = new Random();
		hasProject = false;
		new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-yy");
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

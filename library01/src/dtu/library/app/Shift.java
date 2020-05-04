package dtu.library.app;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class Shift {
	private String workerID;
	private LocalDate data;
	private float time;

	public Shift(String workerID, String data, float time) {
		this.workerID = workerID;
		this.data = stringToDate(data);
		this.time = time;
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
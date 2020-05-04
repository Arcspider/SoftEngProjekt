package dtu.library.app;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class Shift {
	private String workerID;
	private LocalDate date;
	private double time;

	public Shift(String workerID, LocalDate date, double time) {
		this.workerID = workerID;
		this.date = date;
		this.time = time;
	}
	
	public String toString() {
		return "Worker: " + workerID + " Date: "+ date +" Hours: " + time;
		
	}

	public String getWorkerID() {
		return workerID;
	}


	public LocalDate getDate() {
		return date;
	}
	public void addTime(double timeToAdd) {
		time = time + timeToAdd;
	}
	
	

}
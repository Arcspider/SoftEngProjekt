package projectManagerObjects;
//Daniel(s194592) har haft ansvaret for denne klasse.
import java.time.LocalDate;

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

	public void addTime(double parseDouble) {
		time += parseDouble;
	}

	public Double getHours() {
		return time;
	}

	public void setTime(double d) {
		time = d;
	}


}

package projectManagerObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class timeManagement {
    private int allocatedTime;
    private Date startDate, endDate;
    private Calendar calendar;
    private DateFormat dateFormat;

    public timeManagement(Calendar calendar, DateFormat dateFormat){
        this.calendar = calendar;
        this.dateFormat = dateFormat;
    }

    public Date getStarDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public int getAllocatedTime(){
        return allocatedTime;
    }
}

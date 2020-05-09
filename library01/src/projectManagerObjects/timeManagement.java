package projectManagerObjects;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class timeManagement {
    private int allocatedTime;
    private Date startDate, endDate;
    public timeManagement(Calendar calendar, DateFormat dateFormat){
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

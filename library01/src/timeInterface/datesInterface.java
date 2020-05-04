package timeInterface;

import java.time.LocalDate;
import java.time.Period;

public interface datesInterface {
    public LocalDate getStartDate();
    public LocalDate getEndDate();
    public boolean isOverdue();
    public void setStartDate(LocalDate startDate);
    public void setEndDate(LocalDate endDate);
}

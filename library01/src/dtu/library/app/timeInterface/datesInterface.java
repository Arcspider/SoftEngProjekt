package dtu.library.app.timeInterface;

import java.time.LocalDate;
import java.time.Period;

public interface datesInterface {
    LocalDate getStartDate();
    LocalDate getEndDate();
    boolean isOverdue();
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);
}

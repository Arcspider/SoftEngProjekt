package timeInterface;

import java.time.LocalDate;

public interface datesInterface {
    LocalDate getStartDate();
    LocalDate getEndDate();
    boolean isOverdue();
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);
}

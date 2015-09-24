package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by alexandr on 22.09.15.
 */
public class MealsFilter {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public MealsFilter() {
        this.dateFrom = LocalDate.MIN;
        this.dateTo = LocalDate.MAX;
        this.timeFrom = LocalTime.MIN;
        this.timeTo = LocalTime.MAX;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }
    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }
}

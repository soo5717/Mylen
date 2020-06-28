package com.example.mylen.feature.calendar;

public class CalendarDateItems {
    String calendar_date_date;
    String calendar_date_start;
    String calendar_date_end;
    String wear_hours;
    String to;
    String from;

    public CalendarDateItems(String calendar_date_date, String calendar_date_start, String calendar_date_end, String wear_hours) {
        this.calendar_date_date = calendar_date_date;
        this.calendar_date_start = calendar_date_start;
        this.calendar_date_end = calendar_date_end;
        this.wear_hours = wear_hours;
        this.to = to;
        this.to = from;
    }

    public String getDateDate() {
        return calendar_date_date;
    }

    public void setDateDate(String calendar_date_date) {
        this.calendar_date_date = calendar_date_date;
    }

    public String getStart() {
        return calendar_date_start;
    }

    public void setStart(String calendar_date) { this.calendar_date_start = calendar_date_start; }

    public String getEnd() {
        return calendar_date_end;
    }

    public void setEnd(String calendar_dday) {
        this.calendar_date_end = calendar_date_end;
    }

    public String gethours() {
        return wear_hours;
    }

    public void sethours(String wear_hours) {
        this.wear_hours = wear_hours;
    }

}

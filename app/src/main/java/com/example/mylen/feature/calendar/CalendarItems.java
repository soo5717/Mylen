package com.example.mylen.feature.calendar;

public class CalendarItems {
    String calendar_comment;
    String calendar_date;
    String calendar_dday;

    public CalendarItems(String calendar_comment, String calendar_date, String calendar_dday) {
        this.calendar_comment = calendar_comment;
        this.calendar_date = calendar_date;
        this.calendar_dday = calendar_dday;
    }

    public String getComment() {
        return calendar_comment;
    }

    public void setComment(String calendar_comment) {
        this.calendar_comment = calendar_comment;
    }

    public String getDate() {
        return calendar_date;
    }

    public void setDate(String calendar_date) { this.calendar_date = calendar_date; }

    public String getDday() {
        return calendar_dday;
    }

    public void setDday(String calendar_dday) {
        this.calendar_dday = calendar_dday;
    }

}

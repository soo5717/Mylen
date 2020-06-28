package com.example.mylen.data.calendar;

import com.google.gson.annotations.SerializedName;

public class CalendarDateResponse {
    @SerializedName("wearDate")
    private String[] wearDate;

    @SerializedName("wearStart")
    private String[] wearStart;

    @SerializedName("wearEnd")
    private String[] wearEnd;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String[] getWearDate() {
        return wearDate;
    }

    public String[] getWearStart(){
        return wearStart;
    }

    public String[] getWearEnd() {
        return wearEnd;
    }

    public String getDateSuccess(){
        return success;
    }

    public String getDateMessage() {
        return message;
    }
}



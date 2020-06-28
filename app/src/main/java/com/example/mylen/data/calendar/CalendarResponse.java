package com.example.mylen.data.calendar;

import com.google.gson.annotations.SerializedName;

public class CalendarResponse {
    @SerializedName("lensExp")
    private String[] lensExp;

    @SerializedName("lensWear")
    private String[] lensWear;

    @SerializedName("lensOpen")
    private String[] lensOpen;

    @SerializedName("liquidExp")
    private String[] liquidExp;

    @SerializedName("liquidOpen")
    private String[] liquidOpen;

    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String[] getLensExp() {
        return lensExp;
    }

    public String[] getLensWear(){
        return lensWear;
    }

    public String[] getLensOpen() {
        return lensOpen;
    }

    public String[] getLiquidExp(){
        return liquidExp;
    }

    public String[] getLiquidOpen(){
        return liquidOpen;
    }

    public String getCalendarSuccess(){
        return success;
    }

    public String getCalendarMessage() {
        return message;
    }
}



package com.example.mylen.data.notice;

import com.google.gson.annotations.SerializedName;

public class NoticeResponse {
    @SerializedName("userId")
    private int userId;

    @SerializedName("msgArray")
    private String[] msgArray;

    @SerializedName("dateArray")
    private String[] dateArray;

    @SerializedName("timeArray")
    private String[] timeArray;


    public int getUserId() {
        return userId;
    }

    public String[] getmsgArray() { return msgArray; }

    public String[] getdateArray() { return dateArray; }

    public String[] gettimeArray() { return timeArray; }
}

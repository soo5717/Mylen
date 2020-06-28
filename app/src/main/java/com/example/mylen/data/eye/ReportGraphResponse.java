package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class ReportGraphResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("weekPointArray")
    private int[] weekPointArray;

    @SerializedName("weekCountArray")
    private int[] weekCountArray;

    public int getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int[] getWeekPointArray() { return weekPointArray; }

    public int[] getWeekCountArray() { return weekCountArray; }

}

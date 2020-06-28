package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class ReportResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("totalPoint")
    private int totalPoint;

    @SerializedName("totalCount")
    private int totalCount;

    @SerializedName("averageDailyCount")
    private Float averageDailyCount;

    public int getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int getTotalPoint() { return totalPoint; }

    public int getTotalCount() { return totalCount; }

    public Float getAverageDailyCount() { return averageDailyCount; }

}

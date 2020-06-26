package com.example.mylen.data.user;

import com.google.gson.annotations.SerializedName;

public class StatusResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private Boolean success;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }
}

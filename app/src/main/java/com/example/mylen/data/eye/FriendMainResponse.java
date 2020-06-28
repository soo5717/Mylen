package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class FriendMainResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("success")
    private Boolean success;

//    @SerializedName("pictureArray")
//    private String[] pictureArray;

    @SerializedName("nameArray")
    private String[] nameArray;

    @SerializedName("emailArray")
    private String[] emailArray;

    @SerializedName("pointArray")
    private String[] pointArray;


//    public String[] getpictureArray() {
//        return pictureArray;
//    }

    public int getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String[] getNameArray() { return nameArray; }

    public String[] getEmailArray() { return emailArray; }

    public String[] getPointArray() { return pointArray; }
}

package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class FriendMainResponse1 {

    @SerializedName("status")
    private int status;

    @SerializedName("success")
    private Boolean success;

//    @SerializedName("pictureArray")
//    private String[] pictureArray;

    @SerializedName("friendIdArray")
    private int[] friendIdArray;
    @SerializedName("pointArray")
    private int[] pointArray;


//    public String[] getpictureArray() {
//        return pictureArray;
//    }

    public int getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int[] getFriendIdArray() { return friendIdArray; }

    public int[] getPointArray() { return pointArray; }
}

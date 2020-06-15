package com.example.mylen.data.exercise;

import com.google.gson.annotations.SerializedName;

public class SearchFriendResponse {
    //    @SerializedName("pictureArray")
    //    private String[] pictureArray;

    @SerializedName("userId")
    private int userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userEmail")
    private String userEmail;


    //    public String[] getpictureArray() {
    //        return pictureArray;
    //    }


    public int getUserId() {
        return userId;
    }

    public String getuserName() { return userName; }


    public String getuserEmail() { return userEmail; }
}

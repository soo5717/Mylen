package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class SearchAddFriendResponse {
    //    @SerializedName("pictureArray")
    //    private String[] pictureArray;

    @SerializedName("id")
    private int userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

//    @SerializedName("img")
//    private String userImg;


    public int getUserId() {
        return userId;
    }

    public String getUserName() { return userName; }

    public String getUserEmail() { return userEmail; }

    //    public String[] getUserImg() { return userImg; }
}

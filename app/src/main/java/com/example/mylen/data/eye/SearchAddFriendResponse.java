package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class SearchAddFriendResponse {
    //    @SerializedName("pictureArray")
    //    private String[] pictureArray;

    @SerializedName("searchId")
    private int searchId;

    @SerializedName("searchName")
    private String searchName;

    @SerializedName("searchEmail")
    private String searchEmail;

//    @SerializedName("searchImg")
//    private String searchImg;


    public int getUserId() {
        return searchId;
    }

    public String getUserName() { return searchName; }

    public String getUserEmail() { return searchEmail; }

    //    public String[] getUserImg() { return searchImg; }
}

package com.example.mylen.data.exercise;

import com.google.gson.annotations.SerializedName;

public class FriendMainResponse {

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

    public String[] getnameArray() { return nameArray; }

    public String[] getemailArray() { return emailArray; }

    public String[] getpointArray() { return pointArray; }
}

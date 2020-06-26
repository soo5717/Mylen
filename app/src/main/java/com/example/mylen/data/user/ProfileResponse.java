package com.example.mylen.data.user;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("name")
    private String name;
    @SerializedName("birth")
    private String birth;
    @SerializedName("leftSph")
    private Float left_sph;
    @SerializedName("rightSph")
    private Float right_sph;

    public int getStatus() {
        return status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public Float getLeft_sph() {
        return left_sph;
    }

    public Float getRight_sph() {
        return right_sph;
    }
}

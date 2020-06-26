package com.example.mylen.data.user;

import com.google.gson.annotations.SerializedName;

public class ProfileData {
    @SerializedName("name")
    String name;
    @SerializedName("birth")
    private String birth;
    @SerializedName("leftSph")
    private String left_sph;
    @SerializedName("rightSph")
    private String right_sph;

    public ProfileData(String name, String birth, String left_sph, String right_sph) {
        this.name = name;
        this.birth = birth;
        this.left_sph = left_sph;
        this.right_sph = right_sph;
    }
}

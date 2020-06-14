package com.example.mylen.data.user;

import com.google.gson.annotations.SerializedName;

public class SignUpData {
    @SerializedName("email")
    private String email;
    @SerializedName("pwd")
    private String pwd;
    @SerializedName("name")
    private String name;
    @SerializedName("birth")
    private String birth;

    public SignUpData(String email, String pwd, String name, String birth) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
    }
}



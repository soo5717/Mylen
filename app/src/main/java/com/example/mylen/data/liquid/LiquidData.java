package com.example.mylen.data.liquid;

import com.google.gson.annotations.SerializedName;

public class LiquidData {
    @SerializedName("brand")
    String brand;
    @SerializedName("name")
    String name;
    @SerializedName("expDate")
    String expDate;
    @SerializedName("openDate")
    String openDate;
    @SerializedName("status")
    int status;

    public LiquidData(String brand, String name, String expDate, String openDate, int status) {
        this.brand = brand;
        this.name = name;
        this.expDate = expDate;
        this.openDate = openDate;
        this.status = status;
    }
}

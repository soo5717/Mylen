package com.example.mylen.data.lens;

import com.google.gson.annotations.SerializedName;

public class LensData {
    @SerializedName("brand")
    String brand;
    @SerializedName("name")
    String name;
    @SerializedName("quantity")
    String quantity;
    @SerializedName("wearDate")
    String wearDate;
    @SerializedName("expDate")
    String expDate;
    @SerializedName("openDate")
    String openDate;
    @SerializedName("status")
    int status;

    public LensData(String brand, String name, String quantity, String wearDate, String expDate, String openDate, int status) {
        this.brand = brand;
        this.name = name;
        this.quantity = quantity;
        this.wearDate = wearDate;
        this.expDate = expDate;
        this.openDate = openDate;
        this.status = status;
    }
}

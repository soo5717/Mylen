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
}

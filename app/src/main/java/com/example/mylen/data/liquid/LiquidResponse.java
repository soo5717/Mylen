package com.example.mylen.data.liquid;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LiquidResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("liquidInfo")
    private ArrayList<LiquidInfo> liquidInfo = null;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ArrayList<LiquidInfo> getLiquidInfo() {
        return liquidInfo;
    }

    public class LiquidInfo {
        @SerializedName("id")
        public int id;
        @SerializedName("liquid_brand")
        public String brand;
        @SerializedName("liquid_name")
        public String name;
        @SerializedName("liquid_exp_date")
        public String expDate;
        @SerializedName("liquid_open_date")
        public String openDate;
        @SerializedName("liquid_status")
        public int status;
        @SerializedName("user_id")
        public int userId;

        public int getId() {
            return id;
        }

        public String getBrand() {
            return brand;
        }

        public String getName() {
            return name;
        }

        public String getExpDate() {
            return expDate;
        }

        public String getOpenDate() {
            return openDate;
        }

        public int getStatus() {
            return status;
        }

        public int getUserId() {
            return userId;
        }
    }
}

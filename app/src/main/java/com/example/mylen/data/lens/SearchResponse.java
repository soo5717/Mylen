package com.example.mylen.data.lens;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("searchInfo")
    private ArrayList<searchInfo> searchInfo = null;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ArrayList<SearchResponse.searchInfo> getSearchInfo() {
        return searchInfo;
    }

    public class searchInfo{
        @SerializedName("search_brand")
        String brand;
        @SerializedName("search_name")
        String name;
        @SerializedName("search_quantity")
        String quantity;
        @SerializedName("search_wear_date")
        String wearDate;
        @SerializedName("search_type")
        String type;

        public String getBrand() {
            return brand;
        }

        public String getName() {
            return name;
        }

        public String getQuantity() {
            return quantity;
        }

        public String getWearDate() {
            return wearDate;
        }

        public String getType() {
            return type;
        }
    }
}

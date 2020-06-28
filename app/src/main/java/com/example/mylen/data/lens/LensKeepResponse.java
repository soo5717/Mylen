package com.example.mylen.data.lens;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LensKeepResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("lensInfo")
    public List<LensInfo> lensInfo = null;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public List<LensInfo> getLensInfo() {
        return lensInfo;
    }

    public class LensInfo {
        @SerializedName("id")
        public int id;
        @SerializedName("lens_brand")
        public String brand;
        @SerializedName("lens_name")
        public String name;
        @SerializedName("lens_quantity")
        public String quantity;
        @SerializedName("lens_wear_date")
        public String wearDate;
        @SerializedName("lens_exp_date")
        public String expDate;
        @SerializedName("lens_open_date")
        public String  openDate;
        @SerializedName("lens_status")
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

        public String getQuantity() {
            return quantity;
        }

        public String getWearDate() {
            return wearDate;
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

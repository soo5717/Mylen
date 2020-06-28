package com.example.mylen.data.lens;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LensOpenResponse {
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
        @SerializedName("lens_name")
        public String name;
        @SerializedName("lens_brand")
        public String brand;
        @SerializedName("lens_exp_date")
        public String expDate;
        @SerializedName("case_date")
        public String caseDate;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getBrand() {
            return brand;
        }

        public String getExpDate() {
            return expDate;
        }

        public String getCaseDate() {
            return caseDate;
        }
    }
}

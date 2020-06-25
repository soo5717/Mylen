//noticeset 버튼의 변경사항을 server로 전달
package com.example.mylen.data.notice;

import com.google.gson.annotations.SerializedName;

public class UpdateNoticeSetData {
    @SerializedName("userId")
    int userId;

    @SerializedName("setId")
    int setId;

    @SerializedName("buttonId")
    int buttonId;

    public UpdateNoticeSetData(int userId, int setId, int buttonId) {
        this.userId = userId;
        this.setId = setId;
        this.buttonId = buttonId;
    }
}

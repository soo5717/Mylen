//noticeset 버튼의 변경사항을 server로 전달
package com.example.mylen.data.notice.set;

import com.google.gson.annotations.SerializedName;

public class UpdateNoticeSetData {
    @SerializedName("userId")
    int userId;

    @SerializedName("setId")
    int setId;

    @SerializedName("onOffArray")
    int[] onOffArray;

    public UpdateNoticeSetData(int userId, int setId, int[] onOffArray) {
        this.userId = userId;
        this.setId = setId;
        this.onOffArray = onOffArray;
    }
}
//알림 띄워야하는 정보들 가져오기
package com.example.mylen.data.notice;

import com.google.gson.annotations.SerializedName;

public class NoticeData {
    @SerializedName("userId")
    int userId;

    public NoticeData(int userId) {
        this.userId = userId;
    }
}

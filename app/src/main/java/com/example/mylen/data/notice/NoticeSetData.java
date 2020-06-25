//noticeset 버튼의 변경사항을 server로 전달
package com.example.mylen.data.notice;

import com.google.gson.annotations.SerializedName;

public class NoticeSetData {
    @SerializedName("userId")
    int userId;


    public NoticeSetData(int userId) {
        this.userId = userId;
    }
}

//렌즈 등록 시에, DB notice 테이블에 정보입력
package com.example.mylen.data.notice.notice;

import com.google.gson.annotations.SerializedName;

public class AddNoticeData {
    @SerializedName("userId")
    private int userId;

    @SerializedName("setId")
    private int setId;

    @SerializedName("noticeDatetime")
    private String noticeDatetime;

    public AddNoticeData(int userId, int setId, String noticeDatetime) {
        this.userId = userId;
        this.setId = setId;
        this.noticeDatetime = noticeDatetime;
    }
}

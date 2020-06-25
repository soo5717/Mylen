//알림 띄워야하는 정보들 가져오기
package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class SearchAddFriendData {
    @SerializedName("userEmail")
    String userId;

    public SearchAddFriendData(String userEmail) {
        this.userId = userId;
    }
}

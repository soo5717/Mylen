//알림 띄워야하는 정보들 가져오기
package com.example.mylen.data.exercise;

import com.google.gson.annotations.SerializedName;

public class FriendMainData {
    @SerializedName("userId")
    int userId;

    public FriendMainData(int userId) {
        this.userId = userId;
    }
}

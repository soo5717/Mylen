//알림 띄워야하는 정보들 가져오기
package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class SearchFriendData {
    @SerializedName("userEmail")
    String userEmail;

    public SearchFriendData(String userEmail) {
        this.userEmail = userEmail;
    }
}

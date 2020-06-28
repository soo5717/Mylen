//친구 검색 후, 추가하기 버튼 -> friend db에 insert
package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class AddFriendData {
    @SerializedName("friendId")
    private int friendId;

    public AddFriendData(int friendId) {
        this.friendId = friendId;
    }
}

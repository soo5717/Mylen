package com.example.mylen.data.eye;

import com.google.gson.annotations.SerializedName;

public class FriendCheckData {
        @SerializedName("friendId")
        private int friendId;

        public FriendCheckData(int friendId) {
            this.friendId = friendId;
        }
}


//알림 띄워야하는 정보들 가져오기
package com.example.mylen.data.calendar;

import com.google.gson.annotations.SerializedName;

public class CalendarData {
    @SerializedName("userId")
    int userId;

    public CalendarData(int userId) {
        this.userId = userId;
    }

}

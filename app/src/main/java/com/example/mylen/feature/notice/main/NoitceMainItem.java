package com.example.mylen.feature.notice.main;

public class NoitceMainItem {
    String message;
    String msg_date;

    public NoitceMainItem(String message, String msg_date) {
        this.message = message;
        this.msg_date = msg_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg_date() {
        return msg_date;
    }

    public void setMsg_date(String msg_date) {
        this.msg_date = msg_date;
    }
}

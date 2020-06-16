package com.example.mylen.feature.eye.main;

public class EyeMainFriendItem {
    String rank;
    //Image picture;
    String name;
    String email;
    String point;

    public EyeMainFriendItem(String rank, String name, String email, String point) {
        this.rank = rank;
        //this.picture = picture;
        this.name = name;
        this.email = email;
        this.point = point;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

   /* public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}

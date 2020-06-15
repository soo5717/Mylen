package com.example.mylen.feature.exercise.add;

public class EyeAddFriendItem {

    //Image picture;
    String name;
    String email;

    public EyeAddFriendItem(String name, String email) {
        //this.picture = picture;
        this.name = name;
        this.email = email;
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

}

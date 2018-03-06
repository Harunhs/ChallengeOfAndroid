package com.example.harunsofuoglu.challengeofandroid.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harunsofuoglu on 26.02.2018.
 */

public class Person {

    @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Person(int userId,int id,String title){

        this.userId=userId;
        this.id=id;
        this.title=title;

    }



}

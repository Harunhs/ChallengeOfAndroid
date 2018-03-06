package com.example.harunsofuoglu.challengeofandroid.network;

import com.example.harunsofuoglu.challengeofandroid.models.DetailView;
import com.example.harunsofuoglu.challengeofandroid.models.Person;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by harunsofuoglu on 26.02.2018.
 */

public interface ApiService {


    @GET("/albums")
    Call<List<Person>> getUsers();

    @GET("/photos")
    Call<List<DetailView>> getPhotos();




}

   /*
    @FormUrlEncoded
    @POST("/profile/authenticate/")
    Call<Person> postUser(@Field("email") String email,@Field("userName") String userName,@Field("password") String password
            ,@Field("firstName") String firstName,@Field("lastName") String lastName );
    @GET("/profile/detail/")
    Call<Person> getUser(@Query("name") String query);
    */
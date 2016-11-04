package com.example.dell.mdemo.interfaces;

//import com.example.hp.movies.apimodel.MovieDb;

import com.example.dell.mdemo.GetMaptagModel;
import com.example.dell.mdemo.LoginModel;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by hp on 20-02-2016.
 */
public interface MaptagServiceInterface {
    @FormUrlEncoded
    @POST("/getMaptag")
    void confirmSlot(@Field("tagname")String  tagname,Callback<GetMaptagModel> responseCallback);

}

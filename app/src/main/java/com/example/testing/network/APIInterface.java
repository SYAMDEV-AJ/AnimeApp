package com.example.testing.network;


import com.example.testing.modelclass.CharacterDetailsResponse;
import com.example.testing.modelclass.CharacterResponse;
import com.example.testing.modelclass.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("/api/character/?page=")
    Call<CharacterResponse> page(@Query("pno") String pno);

    @GET("/api/character/")
    Call<CharacterDetailsResponse> details(@Query("id") String id);

}
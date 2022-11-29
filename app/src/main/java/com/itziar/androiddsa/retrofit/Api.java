package com.itziar.androiddsa.retrofit;

import com.itziar.androiddsa.domain.MyObjects;
import com.itziar.androiddsa.domain.UserLogIn;
import com.itziar.androiddsa.domain.UserRegister;
import com.itziar.androiddsa.domain.vo.Credentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("User/login/")
    Call<UserLogIn> logInUser(@Body Credentials credentials);

    @POST("/user")
    Call<UserRegister> registerUser(@Body UserRegister userRegister);

    @GET("MyObjects/")
    Call<List<MyObjects>> getListObjects();


}

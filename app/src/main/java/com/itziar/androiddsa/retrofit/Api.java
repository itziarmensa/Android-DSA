package com.itziar.androiddsa.retrofit;

import com.itziar.androiddsa.domain.UserLogIn;
import com.itziar.androiddsa.domain.UserRegister;
import com.itziar.androiddsa.domain.vo.Credentials;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("login")
    Call<UserLogIn> logInUser(@Body Credentials credentials);

    @POST("/user")
    Call<UserRegister> registerUser(@Body UserRegister userRegister);


}

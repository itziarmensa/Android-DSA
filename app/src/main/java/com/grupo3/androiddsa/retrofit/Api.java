package com.grupo3.androiddsa.retrofit;

import com.grupo3.androiddsa.domain.Characters;
import com.grupo3.androiddsa.domain.MyObjects;
import com.grupo3.androiddsa.domain.User;
import com.grupo3.androiddsa.domain.to.ObjectRecycler;
import com.grupo3.androiddsa.domain.to.UserRegister;
import com.grupo3.androiddsa.domain.vo.Credentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    String URL = "http://147.83.7.205:80/dsaApp/";

    @POST("gameManager/user/login")
    Call<Void> logInUser(@Body Credentials credentials);

    @POST("gameManager/user")
    Call<User> registerUser(@Body UserRegister userRegister);

    @GET("gameManager/myObjects")
    Call<List<MyObjects>> getListObjects();

    @GET("gameManager/characters")
    Call<List<Characters>> getListCharacters();

    @GET("gameManager/users")
            Call<List<User>> getListUsers();

    @PUT("gameManager/user/buyObject/{email}/{objectId}")
    Call<Void> buyObject(@Path("email") String email, @Path("objectId") String objectId);

    ///gameManager/user/buyCharacter/{email}/{characterId}
    //buy a Character

    @PUT("gameManager/user/buyCharacter/{email}/{characterId}")
    Call<Void> buyCharacter(@Path("email") String email, @Path("characterId") String objectId);

    @GET("gameManager/user/{email}/myObjects")
    Call<List<MyObjects>> getMyObjects(@Path("email") String email);

    @GET("gameManager/user/{email}/characters")
    Call<List<Characters>> getMyCharacters(@Path("email") String email);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

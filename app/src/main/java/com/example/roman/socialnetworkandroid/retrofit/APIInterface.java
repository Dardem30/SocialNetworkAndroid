package com.example.roman.socialnetworkandroid.retrofit;

import com.example.roman.socialnetworkandroid.model.PhotoList;
import com.example.roman.socialnetworkandroid.model.User;
import com.example.roman.socialnetworkandroid.model.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @POST("auth")
    Call<String> auth(@Body UserDto userDto);
    @GET("usersByUsername/{username}")
    Call<User> userByUsername(@Path("username")String username);

//    @POST("signUp")
//    Call<UserRegistry> signUp(@Body UserRegistry userRegistry);
//
//    @POST("photoAndroid/{username}")
//    Call<UserRegistry> savePhoto(@Path("username") String username,@Body String url);

    @GET("userPhoto/{userId}")
    Call<PhotoList> getMainPhoto(@Path("userId") int userId);

}

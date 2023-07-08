package com.example.ecommerce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_interface
{
   @FormUrlEncoded
    @POST("register.php")
    Call<RegisterClass>REGISTER_CLASS_CALL(@Field("name")String name,@Field("email")String email,@Field("password")String password);
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginClass>LOGIN_CLASS_CALL(@Field("email")String email,@Field("password")String password);
}

package com.example.ecommerce;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    public static Api_interface  callapi()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://pradipecommerce.000webhostapp.com/MySite/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api_interface api_interface=retrofit.create(Api_interface.class);
        return  api_interface;
    }
}

package com.aleksarm.randomuserrecviewmvvm.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {
    public static final String Base_URL = "https://randomuser.me/api/";

    private static Retrofit getRetroInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static ApiService getApiService(){
        return getRetroInstance().create(ApiService.class);
    }
}
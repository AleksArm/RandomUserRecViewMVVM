package com.aleksarm.randomuserrecviewmvvm.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("?results=5000")
    Call<UserList> getUsersList();
}

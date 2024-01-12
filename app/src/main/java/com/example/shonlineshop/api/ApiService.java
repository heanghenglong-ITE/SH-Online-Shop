package com.example.shonlineshop.api;

import com.example.shonlineshop.Domain.ActivityDomain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("host_api/SH-Shop.json")
    Call<List<ActivityDomain>> GetHomeShShop();

    @GET("host_api/Sh-Shop2.json")
    Call<List<ActivityDomain>> GetShShop2();

    @GET("host_api/Sh-Shop3.json")
    Call<List<ActivityDomain>> GetShShop3();
}


package com.example.tiago.establishmentexample.network;


import com.example.tiago.establishmentexample.utils.ClientConfigs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiago on 09/10/2017.
 */

public class EstablishmentProvider {
    private EstablishmentService mService;
    private Retrofit mRetrofitClient;

    public EstablishmentProvider() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new UTCDateTypeAdapter())
                .create();
        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ClientConfigs.REST_API_BASER_URL)
                 .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mService = mRetrofitClient.create(EstablishmentService.class);
    }

    public EstablishmentService getmService() {
        return mService;
    }

    public Retrofit getmRetrofitClient() {
        return mRetrofitClient;
    }

}

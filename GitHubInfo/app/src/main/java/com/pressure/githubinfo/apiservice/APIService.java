package com.pressure.githubinfo.apiservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    private static  String base_url = "https://api.github.com/";
    private static Retrofit retrofit;

    public static Retrofit getClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(base_url)
                                                       .addConverterFactory(GsonConverterFactory.create())
                                                       .build();

        }
        return retrofit;
    }
}

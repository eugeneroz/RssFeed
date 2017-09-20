package com.eugene.rssfeed.network;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by eugene on 20/09/2017.
 */

public class NetworkManager {
    private static final NetworkManager ourInstance = new NetworkManager();
    private final Webservice webservice;

    private NetworkManager() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://www.globes.co.il")
                .addConverterFactory(SimpleXmlConverterFactory.create());
        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor(message -> Logger.t(Retrofit.class.getSimpleName()).d(message));

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(15000L, TimeUnit.MILLISECONDS);
        httpClient.connectTimeout(60000L, TimeUnit.MILLISECONDS);

        httpClient.addInterceptor(loggingInterceptor);


        webservice = builder.client(httpClient.build()).build().create(Webservice.class);
    }

    public static Webservice getWebservice() {
        return ourInstance.webservice;
    }
}

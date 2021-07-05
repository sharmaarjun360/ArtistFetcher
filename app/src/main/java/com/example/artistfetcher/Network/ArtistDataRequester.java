package com.example.artistfetcher.Network;

import android.net.Uri;
import android.util.Log;

import com.example.artistfetcher.BuildConfig;
import com.example.artistfetcher.Constants.Constants;
import com.example.artistfetcher.Interface.InterfaceEndPointITunes;

import java.io.IOException;
import java.util.Collections;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arjunsharma on 04,July,2021
 */
public final class ArtistDataRequester {
    private static ArtistDataRequester artistDataRequester;
    private static InterfaceEndPointITunes interfaceEndPointITunes = null;
    private ArtistDataRequester() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interfaceEndPointITunes =  retrofit.create(InterfaceEndPointITunes.class);
    }

    public static InterfaceEndPointITunes getClient(){
        if(interfaceEndPointITunes == null) {
            final Retrofit retrofit;
            if(BuildConfig.DEBUG){
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }else {
                final OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new FakeInterceptor()).build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            }
            interfaceEndPointITunes = retrofit.create(InterfaceEndPointITunes.class);
        }
        return interfaceEndPointITunes;
    }

}

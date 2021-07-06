package com.example.artistfetcher.Interface;

import com.example.artistfetcher.data.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by arjunsharma on 01,July,2021
 */
public interface InterfaceEndPointITunes {

    @GET("/search")
    Call<Result> getListOfAvailableArtistData(@Query("term") String artistName);
}

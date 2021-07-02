package com.example.artistfetcher.data.model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by arjunsharma on 01,July,2021
 */
class Track {

    public static final String ARTIST_NAME = "artistName";
    public static final String TRACK_NAME = "trackName";
    public static final String TRACK_PRICE = "trackPrice";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String PRIMARY_GENRE_NAME = "primaryGenreName";
    //"artistName": "A. R. Rahman & The Pussycat Dolls",
    //"trackName": "Jai Ho! (You Are My Destiny) [feat. Nicole Scherzinger]",
    //"trackPrice": 1.29,
    //"releaseDate": "2009-02-23T12:00:00Z"
//    "primaryGenreName": "Pop"
    public final String artistName;
    public final String trackName;
    public final double trackPrice;
    public final String releaseDate;
    public final String primaryGenreName;

    private Track(String artistName, String trackName, double trackPrice, String releaseDate, String primaryGenreName) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.trackPrice = trackPrice;
        this.releaseDate = releaseDate;
        this.primaryGenreName = primaryGenreName;
    }

    public static Track trackReadFromStream(InputStream stream){
        /*first fail*/
//        return null;
        String artistName = null;
        String trackName = null;
        double trackPrice = -1.0;
        String releaseDate = null;
        String primaryGenreName = null;

        /* second fail */
//        return new Track(artistName, trackName, trackPrice, releaseDate, primaryGenreName);

        Gson gson = new Gson();
        Result result  = gson.fromJson(new InputStreamReader(stream), Result.class);
        artistName = result.getResults().get(0).artistName;
        trackName = result.getResults().get(0).trackName;
        trackPrice = result.getResults().get(0).trackPrice;
        releaseDate = result.getResults().get(0).releaseDate;
        primaryGenreName = result.getResults().get(0).primaryGenreName;

        return new Track(artistName, trackName, trackPrice, releaseDate, primaryGenreName);

                /* Another way of parsing manual way  todo: Can remove before submission
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for(String line = bufferedReader.readLine(); line !=null; line = bufferedReader.readLine()){
                stringBuilder.append(line);
            }
            final JSONObject obj = new JSONObject(stringBuilder.toString());
            final JSONArray results = obj.getJSONArray("results");
            final JSONObject track = results.getJSONObject(0);
            artistName = track.getString(ARTIST_NAME);
            trackName = track.getString(TRACK_NAME);
            trackPrice = track.getDouble(TRACK_PRICE);
            releaseDate = track.getString(RELEASE_DATE);
            primaryGenreName = track.getString(PRIMARY_GENRE_NAME);
            final int resultCount = obj.getInt("resultCount");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }*/
    }
}

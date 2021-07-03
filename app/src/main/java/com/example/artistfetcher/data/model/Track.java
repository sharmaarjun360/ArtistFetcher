package com.example.artistfetcher.data.model;

import com.example.artistfetcher.Constants.Constants;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import okhttp3.internal.http.HttpHeaders;

/**
 * Created by arjunsharma on 01,July,2021
 */
public class Track {

    private static final String ARTIST_NAME = "artistName";
    private static final String TRACK_NAME = "trackName";
    private static final String TRACK_PRICE = "trackPrice";
    private static final String RELEASE_DATE = "releaseDate";
    private static final String PRIMARY_GENRE_NAME = "primaryGenreName";

    private final String artistName;
    private final String trackName;
    private final double trackPrice;
    private final String releaseDate;
    private final String primaryGenreName;
    /*additional good to have properties todo: can definitely use builder patter with options*/
//    private final String artistViewUrl;
    private final String artworkUrl100;
//    private final String currency;


    public String getArtistName() {
        return artistName==null?Constants.EMPTY_STATE:artistName;
    }

    public String getTrackName() {
        return trackName==null?Constants.EMPTY_STATE:trackName;
    }

    public double getTrackPrice() {
        return trackPrice;
    }

    public String getReleaseDate() {
        return releaseDate==null?Constants.EMPTY_STATE:releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName==null?Constants.EMPTY_STATE:primaryGenreName;
    }

    public String getArtworkUrl100() { return artworkUrl100; }

    private Track(String artistName, String trackName, double trackPrice, String releaseDate, String primaryGenreName, String artworkUrl100) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.trackPrice = trackPrice;
        this.releaseDate = releaseDate;
        this.primaryGenreName = primaryGenreName;
        this.artworkUrl100 = artworkUrl100;
    }

    public static Track trackReadFromStream(InputStream stream){
        /*first fail*/
//        return null;
        String artistName = null;
        String trackName = null;
        double trackPrice = -1.0;
        String releaseDate = null;
        String primaryGenreName = null;
        String artistWork = null;

        /* second fail */
//        return new Track(artistName, trackName, trackPrice, releaseDate, primaryGenreName);

        Gson gson = new Gson();
        Result result  = gson.fromJson(new InputStreamReader(stream), Result.class);
        artistName = result.getResults().get(0).artistName;
        trackName = result.getResults().get(0).trackName;
        trackPrice = result.getResults().get(0).trackPrice;
        releaseDate = result.getResults().get(0).releaseDate;
        primaryGenreName = result.getResults().get(0).primaryGenreName;
        artistWork = result.getResults().get(0).artworkUrl100;

        return new Track(artistName, trackName, trackPrice, releaseDate, primaryGenreName,artistWork);
    }



}

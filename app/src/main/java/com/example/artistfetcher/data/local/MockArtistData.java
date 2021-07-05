package com.example.artistfetcher.data.local;

import com.example.artistfetcher.data.model.Result;
import com.example.artistfetcher.data.model.Track;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by arjunsharma on 04,July,2021
 */
public class MockArtistData implements TrackDataSource{
    private Class classContext;
    private final InputStream stream;
    private static MockArtistData mockArtistData;
    private MockArtistData(Class classContext) {
        this.classContext = classContext;
        this.stream = classContext.getResourceAsStream("/sample/1.txt");
    }

    public static MockArtistData getInstance(Class context){
        if(mockArtistData ==null){
            mockArtistData = new MockArtistData(context);
        }
        return mockArtistData;
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
        Track track = result.getResults().get(0);
        artistName = result.getResults().get(0).getArtistName();
        trackName = result.getResults().get(0).getTrackName();
        trackPrice = result.getResults().get(0).getTrackPrice();
        releaseDate = result.getResults().get(0).getReleaseDate();
        primaryGenreName = result.getResults().get(0).getPrimaryGenreName();
        artistWork = result.getResults().get(0).getArtworkUrl100();
        return track;
    }

    public static Track readTrackAtIndexFromStream(InputStream stream,int index){
        Gson gson = new Gson();
        Result result  = gson.fromJson(new InputStreamReader(stream), Result.class);
        Track track = result.getResults().get(index);
        return track;
    }

    public static List<Track> readAllTracksFromStream(InputStream stream){
        Gson gson = new Gson();
        Result result  = gson.fromJson(new InputStreamReader(stream), Result.class);
        List <Track> tracks = result.getResults();
        return tracks;
    }

    @Override
    public Track readTrackFromStream(InputStream stream) {
        Track track = MockArtistData.trackReadFromStream(stream);
        return track;
    }
}

package com.example.artistfetcher.data.model;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by arjunsharma on 01,July,2021
 */
public class TrackTest {

    @Test
    public void trackReadFromStream() {
        InputStream stream = TrackTest.class.getResourceAsStream("/sample/1.txt");
        Track track = Track.trackReadFromStream(stream);
        //first fail
        assertNotNull(track);
        //second fail
        assertNotNull(track.getArtistName());
        assertNotNull(track.getTrackName());
        assertTrue(track.getTrackPrice()>=0);
        assertNotNull(track.getReleaseDate());
        assertNotNull(track.getPrimaryGenreName());
        assertNotNull(track.getArtworkUrl100());
    }
}
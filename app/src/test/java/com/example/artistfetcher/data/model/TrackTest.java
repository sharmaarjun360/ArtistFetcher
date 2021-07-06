package com.example.artistfetcher.data.model;

import com.example.artistfetcher.data.local.MockArtistData;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by arjunsharma on 01,July,2021
 */
public class TrackTest {

    @Test
    public void trackReadFromStream() {
        InputStream stream = TrackTest.class.getResourceAsStream("/sample/1.txt");
        MockArtistData mockArtistData = MockArtistData.getInstance(TrackTest.class);
        Track track = mockArtistData.readTrackFromStream(stream);
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
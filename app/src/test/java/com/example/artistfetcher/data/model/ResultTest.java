package com.example.artistfetcher.data.model;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by arjunsharma on 01,July,2021
 */
public class ResultTest {

    @Test
    public void fetchResultData() {
        InputStream stream = TrackTest.class.getResourceAsStream("/sample/1.txt");
        Result result = Result.fetchResultData(stream);
        //first fail
        assertNotNull(result);
        //second fail
        assertTrue(result.getResultCount()>0);
        assertNotNull(result.getResults());
    }

}
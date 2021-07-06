package com.example.artistfetcher.data.model;

import com.example.artistfetcher.data.local.MockResultData;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by arjunsharma on 01,July,2021
 */
public class ResultTest {

    @Test
    public void fetchResultData() {
        InputStream stream = ResultTest.class.getResourceAsStream("/sample/1.txt");
        MockResultData mockResultData = MockResultData.getInstance(ResultTest.class);
        Result result = mockResultData.fetchResultData(stream);
        //first fail
        assertNotNull(result);
        //second fail
        assertTrue(result.getResultCount()>0);
        assertNotNull(result.getResults());
    }

}
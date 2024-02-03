package com.example.artistfetcher.data.model;

import com.example.artistfetcher.Constants.Constants;
import com.example.artistfetcher.Utils.Utils;
import com.example.artistfetcher.data.local.MockResultData;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    @Test
    public void testSortingLogic(){
        //
        InputStream stream = ResultTest.class.getResourceAsStream("/sample/1.txt");
        MockResultData mockResultData = MockResultData.getInstance(ResultTest.class);
        Result result = mockResultData.fetchResultData(stream);
        //know the correct data before
        Track t1 = result.getResults().get(0);
        Track t2 = result.getResults().get(1);
        // correct order
        List<Track> tempList = new ArrayList<>();
        tempList.add(t1);
        tempList.add(t2);
        List<Track> actual = new ArrayList<>();
        actual.add(t2);
        actual.add(t1);
        // correct order  t2 and t1
        Comparator<Track> comparator = new Comparator<Track>() {
            @Override
            public int compare(Track a, Track b) {
                return Long.compare(
                        Utils.getDateInMilli(Constants.INPUT_DATE_FORMAT,a.getReleaseDate()),
                        Utils.getDateInMilli(Constants.INPUT_DATE_FORMAT,b.getReleaseDate()));
            }
        };

    }

}
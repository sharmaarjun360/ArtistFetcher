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

public class MockResultData implements ResultDataSource{

    private Class context;
    private final InputStream stream;
    private static MockResultData mockResultData;
    private MockResultData(Class context) {
        this.context = context;
        this.stream = context.getResourceAsStream("/sample/1.txt");
    }

    public static MockResultData getInstance(Class context){
        if(mockResultData ==null){
            mockResultData = new MockResultData(context);
        }
        return mockResultData;
    }

    @Override
    public Result fetchResultData(InputStream stream) {
        Result result = MockResultData.fetchMockedResultData(stream);
        return result;
    }

    public static Result fetchMockedResultData(InputStream stream){
        /*first fail*/
//        return null;
        int resultCount = 0;
        List<Track> results = null;
//        /*second fail*/
//        return new Result(resultCount, results);
        Gson gson = new Gson();
        Result result  = gson.fromJson(new InputStreamReader(stream), Result.class);
        resultCount = result.getResultCount();
        results = result.getResults();
        return result;
    }
}

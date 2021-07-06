package com.example.artistfetcher.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arjunsharma on 01,July,2021
 */
public class Result {

    private static final String RESULT_COUNT = "resultCount";
    private static final String RESULTS = "results";

    @SerializedName(RESULT_COUNT)
    private final int resultCount;

    @SerializedName(RESULTS)
    private final List<Track> results;

    public int getResultCount() {
        return resultCount;
    }

    public List<Track> getResults() {
        return results;
    }

    private Result(int resultCount, List<Track> results) {
        this.resultCount = resultCount;
        this.results = results;
    }
//Moved to MockResultData
//    public static Result fetchResultData(InputStream stream){
//        /*first fail*/
////        return null;
//        int resultCount = 0;
//        List<Track> results = null;
////        /*second fail*/
////        return new Result(resultCount, results);
//        Gson gson = new Gson();
//        Result result  = gson.fromJson(new InputStreamReader(stream), Result.class);
//        resultCount = result.getResultCount();
//        results = result.getResults();
//        return new Result(resultCount,results);
//    }

}

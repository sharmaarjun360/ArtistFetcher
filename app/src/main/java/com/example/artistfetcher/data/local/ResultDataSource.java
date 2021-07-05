package com.example.artistfetcher.data.local;

import com.example.artistfetcher.data.model.Result;

import java.io.InputStream;

/**
 * Created by arjunsharma on 03,July,2021
 */
public interface ResultDataSource {

    Result fetchResultData(InputStream stream);

}

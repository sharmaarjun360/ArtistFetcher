package com.example.artistfetcher.data.local;

import com.example.artistfetcher.data.model.Result;
import com.example.artistfetcher.data.model.Track;

import java.io.InputStream;

/**
 * Created by arjunsharma on 05,July,2021
 */
interface TrackDataSource {
    Track readTrackFromStream(InputStream stream);
}

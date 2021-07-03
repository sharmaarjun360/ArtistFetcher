package com.example.artistfetcher.Interface;

import com.example.artistfetcher.data.model.Track;

/**
 * Created by arjunsharma on 02,July,2021
 */
public interface CallbackTrackAdapter {
    public void onTrackSelection(Track trackDetails);
    public void onRecycleViewCreated();
}

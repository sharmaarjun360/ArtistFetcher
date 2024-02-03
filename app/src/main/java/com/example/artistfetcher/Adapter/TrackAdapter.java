package com.example.artistfetcher.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.artistfetcher.Constants.Constants;
import com.example.artistfetcher.Interface.CallbackTrackAdapter;
import com.example.artistfetcher.R;
import com.example.artistfetcher.Utils.Utils;
import com.example.artistfetcher.data.model.Result;
import com.example.artistfetcher.data.model.Track;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by arjunsharma on 01,July,2021
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackCellViewHolder>{

    private Result result = null;
    private Track currentTrackDetails = null;
    private Context context;
    private CallbackTrackAdapter callbackTrackAdapter;
    public TrackAdapter(Result result, Context context, CallbackTrackAdapter callbackTrackAdapter) {
        this.result = result;
        this.context = context;
        this.callbackTrackAdapter = callbackTrackAdapter;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public TrackCellViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_track, parent, false);
        // set the view's size, margins, padding and layout parameters
        TrackCellViewHolder vh = new TrackCellViewHolder(v); // pass the view to View Holder
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: Navigation if need be
            }
        });
        callbackTrackAdapter.onRecycleViewCreated();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TrackCellViewHolder holder, int position) {
        if (result==null || result.getResultCount()==0) return;


        Comparator <Track>comparator = new Comparator<Track>() {
            @Override
            public int compare(Track a, Track b) {
                return Long.compare(
                        Utils.getDateInMilli(Constants.INPUT_DATE_FORMAT,a.getReleaseDate()),
                        Utils.getDateInMilli(Constants.INPUT_DATE_FORMAT,b.getReleaseDate()));
            }
        };
        Collections.sort(result.getResults(),comparator);

        if (result.getResults() == null || result.getResults().size()==0) return;
        currentTrackDetails = result.getResults().get(position);
        holder.context = context;
        holder.populateTrackData(currentTrackDetails);
    }

    @Override
    public int getItemCount() {

        return result==null?0:result.getResultCount();
    }
}

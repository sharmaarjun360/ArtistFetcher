package com.example.artistfetcher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artistfetcher.Constants.Constants;
import com.example.artistfetcher.Interface.CallbackTrackAdapter;
import com.example.artistfetcher.R;
import com.example.artistfetcher.Utils.Utils;
import com.example.artistfetcher.data.model.Result;
import com.example.artistfetcher.data.model.Track;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
class TrackCellViewHolder extends RecyclerView.ViewHolder
{
    // init the item view's

    public Context context;
    private ConstraintLayout root_constraintLayout;
    private final TextView artistName;
    private final TextView trackName;
    private final TextView trackPrice;
    private final TextView releaseDate;
    private final TextView primaryGenreName;
    private final ImageView artWorkURL100;
//    private final String artistViewUrl;
//    private final String currency;

    public TrackCellViewHolder(View itemView)
    {
        super(itemView);
        // get the reference of item view's
        this.root_constraintLayout = itemView.findViewById(R.id.cell_track_container);
        this.artistName = itemView.findViewById(R.id.cell_track_artist_name);
        this.trackName = itemView.findViewById(R.id.cell_track_name);
        this.trackPrice = itemView.findViewById(R.id.cell_track_price);
        this.releaseDate = itemView.findViewById(R.id.cell_track_release_date);
        this.primaryGenreName = itemView.findViewById(R.id.cell_track_primary_genre_name);
        this.artWorkURL100 = itemView.findViewById(R.id.cell_track_artwork);

        addActions();
    }

    public void populateTrackData(Track track){
        artistName.setText("Artist Name: "+ track.getArtistName());
        trackName.setText("Track Name: "+track.getTrackName());
        trackPrice.setText("Price: $ "+track.getTrackPrice()+"");
        releaseDate.setText("Release Date: "+ Utils.getSimpleDate(Constants.INPUT_DATE_FORMAT,Constants.OUTPUT_DATE_FORMAT,track.getReleaseDate()));
        primaryGenreName.setText("Genre: "+ track.getPrimaryGenreName());
        Utils.loadImage((Activity)context, artWorkURL100,track.getArtworkUrl100());
    }

    private void addActions() {
        artWorkURL100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //meh...
            }
        });
    }


}
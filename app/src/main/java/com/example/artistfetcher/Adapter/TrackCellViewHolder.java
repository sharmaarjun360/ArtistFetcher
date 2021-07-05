package com.example.artistfetcher.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artistfetcher.Constants.Constants;
import com.example.artistfetcher.R;
import com.example.artistfetcher.Utils.Utils;
import com.example.artistfetcher.data.model.Track;

import java.lang.reflect.Field;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TrackCellViewHolder extends RecyclerView.ViewHolder {
    // init the item view's

    public static Context context;
    private ConstraintLayout root_constraintLayout;
    private final TextView artistName;
    private final TextView trackName;
    private final TextView trackPrice;
    private final TextView releaseDate;
    private final TextView primaryGenreName;
    private final ImageView artWorkURL100;
    //    private final String artistViewUrl;
//    private final String currency;

    public TrackCellViewHolder(View itemView) {
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

    public void populateTrackData(Track track) {
        artistName.setText("Artist Name: " + track.getArtistName());
        trackName.setText("Track Name: " + track.getTrackName());
        trackPrice.setText("Price: $ " + track.getTrackPrice() + "");
        releaseDate.setText("Release Date: " + Utils.getSimpleDate(Constants.INPUT_DATE_FORMAT, Constants.OUTPUT_DATE_FORMAT, track.getReleaseDate()));
        primaryGenreName.setText("Genre: " + track.getPrimaryGenreName());
        Utils.loadImage((Activity) context, artWorkURL100, track.getArtworkUrl100());
    }

    private void isValid(Track track) {

        track.getClass().getFields();
        for (Field field : track.getClass().getFields()) {
            Object object = new Object();

            try {
                if (field.get(object) == null) {

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

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

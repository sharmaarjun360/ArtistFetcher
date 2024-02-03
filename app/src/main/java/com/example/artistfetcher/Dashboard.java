package com.example.artistfetcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.artistfetcher.Adapter.TrackAdapter;
import com.example.artistfetcher.Fragments.FragmentArtistDetails;
import com.example.artistfetcher.Interface.CallbackTrackAdapter;
import com.example.artistfetcher.Interface.InterfaceEndPointITunes;
import com.example.artistfetcher.Network.ArtistDataRequester;
import com.example.artistfetcher.Utils.EspressoIdlingResource;
import com.example.artistfetcher.data.model.Result;
import com.example.artistfetcher.data.model.Track;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard extends AppCompatActivity implements CallbackTrackAdapter {

    private final String TAG = Dashboard.class.getSimpleName();
    private InterfaceEndPointITunes interfaceEndPointITunes;
    private ProgressBar progressBar; //we want only one
    private SearchView searchView; //we want only one
    private TrackAdapter trackAdapter;
    private RecyclerView recyclerViewListTrack;
    private Button buttonJet;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private FrameLayout fragmentArtistDetailsContainer;
    private FragmentArtistDetails fragmentArtistDetails;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        addAdapters();
        initNetworkService();
        addAction();
    }

    private void addAction() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length()>0){
                getArtistDetails(newText);
                }else{
                    progressBar.setVisibility(View.GONE);
                    refreshAdapter(null);
                }

                return false;
            }
        });
    }

    private void initUI(){
        progressBar = findViewById(R.id.progressBar);
        searchView = findViewById(R.id.search_view);
        recyclerViewListTrack = findViewById(R.id.recycle_view_list_track);
        buttonJet = findViewById(R.id.btn_jet);
        //todo: when want to update for iPAD etc
        if (findViewById(R.id.fragmentContainer) != null) {
            mTwoPane = true;
            fragmentArtistDetailsContainer = findViewById(R.id.fragmentContainer);
            fragmentArtistDetails = new FragmentArtistDetails();
        }
    }


    private void addAdapters() {

        trackAdapter = new TrackAdapter(null, this,this);
        recyclerViewListTrack.setAdapter(trackAdapter);
        recyclerViewListTrack.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListTrack.addItemDecoration(new DividerItemDecoration(recyclerViewListTrack.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void initNetworkService() {
        //created retrofit instance
        interfaceEndPointITunes = ArtistDataRequester.getClient();
    }

    private void getArtistDetails(String artistName){

        progressBar.setVisibility(View.VISIBLE);
        EspressoIdlingResource.increment();
        interfaceEndPointITunes.getListOfAvailableArtistData(artistName).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(!response.isSuccessful()){
                    Log.e(TAG, response.toString());
                    response.message();
                }
                if(response.body() != null){
                    //sort here by release date
                    refreshAdapter((Result) response.body());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.getCause();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void refreshAdapter(Result result) {
        //on UI Thread
        mHandler.post(new Runnable() {
            @Override
            public void run() {

                trackAdapter.setResult(result);
                trackAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                EspressoIdlingResource.decrement();

            }
        });
    }

    @Override
    public void onTrackSelection(Track trackDetails) {

    }

    @Override
    public void onRecycleViewCreated() {
        EspressoIdlingResource.decrement();
        progressBar.setVisibility(View.GONE);
    }
}

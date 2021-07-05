package com.example.artistfetcher;


import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

import com.example.artistfetcher.Adapter.TrackAdapter;
import com.example.artistfetcher.Adapter.TrackCellViewHolder;
import com.example.artistfetcher.Utils.EspressoIdlingResource;
import com.example.artistfetcher.data.local.MockArtistData;
import com.example.artistfetcher.data.model.Track;
import com.example.artistfetcher.test.ArtistRobot;
import com.example.artistfetcher.test.RecyclerViewInteraction;
import com.example.artistfetcher.test.RecyclerViewMatcher;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import javax.security.auth.callback.Callback;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.base.IdlingResourceRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.contrib.RecyclerViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by arjunsharma on 02,July,2021
 */
public class DashboardTest {


    private final Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Rule public ActivityScenarioRule<Dashboard> rule = new ActivityScenarioRule<>(Dashboard.class);

    private final int LIST_ITEM_IN_TEST = 0;

    @Before
    public void registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countIdlingResource);
    }
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countIdlingResource);
    }
        @Test public void A001_testActivity() {
        assertEquals("com.example.artistfetcher", appContext.getPackageName());
        ActivityScenario.launch(Dashboard.class);
    }

    @Test public void A002_testComponents() {
        ActivityScenario.launch(Dashboard.class);
        ArtistRobot artistRobot = new ArtistRobot();
        artistRobot.checkIsAttached(R.id.search_view,R.id.progressBar,R.id.recycle_view_list_track);
        if(isTablet(appContext)){ //checking for additional components when in large screen mode
            onView(withId(R.id.fragmentContainer)).check(matches(not(doesNotExist())));
        }else{
            onView(withId(R.id.fragmentContainer)).check(doesNotExist());
        }
    }

    @Test public void A003_testSearchComponent() {
                onView(withId(R.id.search_view))
                .check(matches(not(doesNotExist())))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(not(isSelected())))
                .perform(click());

    }

    @Test public void A004_testRecycleViewComponent() {

        InputStream stream = getInputStreamFromAssets("1.txt");
        //type a

        //index 02: Barack Obama
        //index 06: Quentin Tarantino
        List<Track> tracks =  MockArtistData.readAllTracksFromStream(stream);
        Track track = tracks.get(LIST_ITEM_IN_TEST);
        onView(withId(R.id.search_view)).perform(typeSearchViewText("a"));
        onView(withId(R.id.recycle_view_list_track))
                .check(matches(not(doesNotExist())))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(not(isSelected())))
                .perform(actionOnItemAtPosition(LIST_ITEM_IN_TEST,click()));

        //type a
        //index 03: Barack Obama
        //index 07: Quentin Tarantino

//        onView(withRecyclerView(R.id.recycle_view_list_track).atPosition(LIST_ITEM_IN_TEST))
//                .check(matches(hasDescendant(withText(track.getArtistName()))));

//        RecyclerViewInteraction.<Track>onRecyclerView(withId(R.id.recycle_view_list_track))
//                .withItems(tracks)
//                .check(new RecyclerViewInteraction.ItemViewAssertion<Track>() {
//                    @Override
//                    public void check(Track item, View view, NoMatchingViewException e) {
//                        matches(hasDescendant(withText(item.getArtistName())))
//                                .check(view, e);
//                    }
//                });

    }

    @Test public void A005_testProgressBarViewComponent() {
        onView(withId(R.id.progressBar))
                .check(matches(not(doesNotExist())))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static ViewAction typeSearchViewText(final String text){
        return new ViewAction(){
            @Override
            public Matcher<View> getConstraints() {
                //only if it is a SearchView and is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView.class));
            }

            @Override
            public String getDescription() {
                return "Change Search view text";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((SearchView) view).setQuery(text,false); ///true = will perform Search i.e. Set text and submit
            }
        };
    }

    private static InputStream getInputStreamFromAssets(String fileName){
        InputStream stream = null;
        try {
            stream = InstrumentationRegistry.getInstrumentation().getContext().getAssets().open(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream;
    }
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcher(recyclerViewId);
    }

}

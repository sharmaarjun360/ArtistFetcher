package com.example.artistfetcher;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

import com.example.artistfetcher.Constants.Constants;
import com.example.artistfetcher.Utils.EspressoIdlingResource;
import com.example.artistfetcher.data.local.MockArtistData;
import com.example.artistfetcher.data.model.Track;
import com.example.artistfetcher.test.ArtistRobot;
import com.example.artistfetcher.test.RecyclerViewMatcher;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.artistfetcher.test.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

/**
 * Created by arjunsharma on 02,July,2021
 */
public class DashboardTest {

    /** the Activity of the Target application */
    private Dashboard mActivity;
    private int resId = R.id.recycle_view_list_track;
    private RecyclerView mRecyclerView;
    private int itemCount = 0;
    private final int LIST_ITEM_IN_TEST = 2;
    private final Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    @Rule public ActivityScenarioRule<Dashboard> rule = new ActivityScenarioRule<Dashboard>(Dashboard.class);

    @Before
    public void registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countIdlingResource);
    }
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countIdlingResource);
    }

    @Test public void A001_testActivity() {
        assertEquals("com.example.artistfetcher"+"."+BuildConfig.FLAVOR, appContext.getPackageName());
        ActivityScenario.launch(Dashboard.class);
    }

    @Test public void A002_testComponents() {
        ActivityScenario.launch(Dashboard.class);
        ArtistRobot artistRobot = new ArtistRobot();
        artistRobot.checkIsAttached(R.id.search_view,R.id.progressBar,resId);
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
        List<Track> tracks =  MockArtistData.readAllTracksFromStream(stream);
        Track track = tracks.get(LIST_ITEM_IN_TEST);
        //type a, index 02: Barack Obama, index 06: Quentin Tarantino
        onView(withId(R.id.search_view)).perform(typeSearchViewText("a"));
        onView(withId(resId))
                .check(matches(not(doesNotExist())))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(not(isSelected())))
                .perform(actionOnItemAtPosition(LIST_ITEM_IN_TEST,click()));

        onView(withId(resId)).check(withItemCount(50));
        onView(withId(resId)).check(withItemCount(greaterThan(0)));
        onView(withId(resId)).check(withItemCount(lessThan(51)));

        //type -> a
        //index 02: Barack Obama, index 06: Quentin Tarantino and like wise
        /* obtaining the Activity from the ActivityScenarioRule */
        this.mActivity = getActivity(rule);
        /* obtaining handles to the U of the Activity */
        this.mRecyclerView = this.mActivity.findViewById(this.resId);
        this.itemCount = this.mRecyclerView.getAdapter().getItemCount();

        if(this.itemCount > 0) {
            for(int i=0; i < this.itemCount; i++) {
                /* clicking the item */
                onView(withId(this.resId))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

                /* check if the ViewHolder is being displayed */
                onView(new RecyclerViewMatcher(this.resId)
                        .atPositionOnView(i, R.id.cell_track_container))
                        .check(matches(isDisplayed()));

                if(BuildConfig.FLAVOR.equals(Constants.PROD) ){
                    //test only one first five
                    /*Difference at mock data and real data
                    observed over time hence skipping*/
                }else {
                    onView(new RecyclerViewMatcher(this.resId)
                            .atPositionOnView(i, R.id.cell_track_artist_name))
                            .check(matches(withText("Artist Name: " + tracks.get(i).getArtistName())));
                }

            }
        }
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

    private <T extends Activity> T getActivity(ActivityScenarioRule<T> activityScenarioRule) {
        AtomicReference<T> activityRef = new AtomicReference<>();
        activityScenarioRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }

}

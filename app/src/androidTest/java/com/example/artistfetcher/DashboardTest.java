package com.example.artistfetcher;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;

import androidx.appcompat.widget.SearchView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.TypeTextAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocused;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by arjunsharma on 02,July,2021
 */
public class DashboardTest {



//    ActivityScenario<Dashboard> scenarioB = ActivityScenario.launch(Dashboard.class);
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    String hint  = context.getResources().getString(R.string.search_hint);
    @Rule public ActivityScenarioRule<Dashboard> rule = new ActivityScenarioRule<>(Dashboard.class);
    @Test public void A001_testActivity() {
//        ActivityScenario.launch(Dashboard.class);
//        ActivityScenario<Dashboard> scenario = rule.getScenario();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.artistfetcher", appContext.getPackageName());
        ActivityScenario.launch(Dashboard.class);
    }
    @Test public void A002_testComponents() {
        ActivityScenario.launch(Dashboard.class);
        onView(withId(R.id.search_view)).check(matches(not(doesNotExist())));
        onView(withId(R.id.progressBar)).check(matches(not(doesNotExist())));
        onView(withId(R.id.recycle_view_list_track)).check(matches(not(doesNotExist())));
        if(isTablet(context)){ //checking for additional components when in large screen mode
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
//        onView(withId(R.id.search_src_text));
//        onView(isAssignableFrom(SearchView.SearchAutoComplete.class)).perform(typeText("No such item"));

    }
    @Test public void A004_testRecycleViewComponent() {
//        ActivityScenario.launch(Dashboard.class);
//        ActivityScenario<Dashboard> scenario = rule.getScenario();
        onView(withId(R.id.recycle_view_list_track))
                .check(matches(not(doesNotExist())))
                .check(matches(isCompletelyDisplayed()))
                .check(matches(not(isSelected())))
                .perform(click());
    }

    @Test public void A005_testProgressBarViewComponent() {
//        ActivityScenario.launch(Dashboard.class);
//        ActivityScenario<Dashboard> scenario = rule.getScenario();
        onView(withId(R.id.progressBar))
                .check(matches(not(doesNotExist())))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
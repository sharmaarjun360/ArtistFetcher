package com.example.artistfetcher.test;

import com.example.artistfetcher.R;
import androidx.annotation.IdRes;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by arjunsharma on 03,July,2021
 */
public abstract class ScreenRobot<T extends ScreenRobot> {

    public T checkIsAttached(@IdRes int... viewIds) {

        for (int viewID : viewIds) {
            onView(withId(viewID)).check(matches(not(doesNotExist())));
        }
        return (T) this;
    }

    public T checkIsCompletelyDisplayed(@IdRes int... viewIds) {
        for (int viewID : viewIds) {
            onView(withId(viewID)).check(matches(isCompletelyDisplayed()));
        }
        return (T) this;
    }

    public T checkIsVisible(@IdRes int... viewIds) {
        for (int viewID : viewIds) {
            onView(withId(viewID)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        }
        return (T) this;
    }

}
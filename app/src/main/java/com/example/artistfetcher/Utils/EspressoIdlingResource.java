package com.example.artistfetcher.Utils;

import androidx.test.espresso.idling.CountingIdlingResource;

/**
 * Created by arjunsharma on 05,July,2021
 */
public class EspressoIdlingResource {

    private static EspressoIdlingResource espressoIdlingResource;
    private static final String RESOURCE  = "CONST";
    public static final CountingIdlingResource countIdlingResource = new CountingIdlingResource(RESOURCE);
    private EspressoIdlingResource() {
    }

    public static EspressoIdlingResource getInstance(){
        if(espressoIdlingResource==null){
            espressoIdlingResource = new EspressoIdlingResource();
        }
        return espressoIdlingResource;
    }

    public static void increment(){
        countIdlingResource.increment();
    }

    public static void decrement(){
        if(!countIdlingResource.isIdleNow()) {
            countIdlingResource.decrement();
        }
    }
}

package com.example.artistfetcher.test;

import androidx.annotation.IdRes;

/**
 * Created by arjunsharma on 03,July,2021
 */
public class ArtistRobot extends ScreenRobot<ArtistRobot> {

    public ArtistRobot isAttached(@IdRes int ...viewIds){
        return checkIsAttached(viewIds);
    }

}

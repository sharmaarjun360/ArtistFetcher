package com.example.artistfetcher.data.local;

import java.util.HashMap;

/**
 * Created by arjunsharma on 03,July,2021
 */
class InMemoryData implements TestDouble{

    //just in case we want in memory data for testing and we have some autocomplete data in shared preferences
    private final HashMap<String,String> map = new HashMap<>();

    @Override
    public String get(String key) {
        return map.getOrDefault(key, null);
    }

    public void put(String key, String value ){
        map.put(key, value);
    }
}

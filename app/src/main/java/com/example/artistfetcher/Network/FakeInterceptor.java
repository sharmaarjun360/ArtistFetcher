package com.example.artistfetcher.Network;

import android.util.Log;

import com.example.artistfetcher.BuildConfig;
import com.example.artistfetcher.Utils.Utils;
import com.example.artistfetcher.data.local.MockResultData;
import com.example.artistfetcher.data.model.Result;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by arjunsharma on 04,July,2021
 */
public class FakeInterceptor implements Interceptor {

    public static final int MOCK_RESPONSE_CODE_SUCCESS = 200;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        if(BuildConfig.DEBUG) { //only in DEBUG Mode
            String responseString;
            // Get Request URI.
            final URI uri = chain.request().url().uri();
            // Get Query String.
            final String query = uri.getQuery();
            // Parse the Query String.
            final String[] parsedQuery = query.split("=");


            //# alternatively we can just use GSON to parse and make MOCK data
//            Result result = MockResultData.fetchMockedResultData(getStream());
//            Gson gson = new Gson();
//            gson.toJson(result);
//            Log.e("Gson: ", gson.toJson(result));
            responseString = getRawString(getStream());

            response = new Response.Builder()
                    .code(MOCK_RESPONSE_CODE_SUCCESS)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        }
        else {
            response = chain.proceed(chain.request());
        }

        return response;
    }

    public String getRawString(InputStream stream){
    InputStreamReader isReader = new InputStreamReader(stream);
    //Creating a BufferedReader object
    BufferedReader reader = new BufferedReader(isReader);
    StringBuffer sb = new StringBuffer();
    String str = null;
      while(true){
          try {
              if (!((str = reader.readLine())!= null)) break;
          } catch (IOException e) {
              e.printStackTrace();
          }
          sb.append(str);
    }
      return sb.toString();
    }

    public InputStream getStream() {
        InputStream stream  = this.getClass().getClassLoader().getResourceAsStream("assets/1.txt");
        return stream;
    }
}

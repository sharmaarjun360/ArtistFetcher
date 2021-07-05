package com.example.artistfetcher.Utils;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.artistfetcher.Constants.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arjunsharma on 02,July,2021
 */
public class Utils {
    //crete this method into your Utils class and call this method wherever you want to use.


    public static void loadImage(final Activity context, ImageView imageView, String url) {
        if (context == null || context.isDestroyed()) return;

        int placeHolderUrl= Constants.DEFAULT_IMAGE_RESOURCE;
        int errorImageUrl= Constants.DEFAULT_IMAGE_RESOURCE;
        Glide.with(context)
                .load(url)
                .placeholder(placeHolderUrl)
                .error(errorImageUrl)
                .into(imageView); //pass imageView reference to appear the image.
    }

    public static String getSimpleDate(String inputDateFormat, String outputDateFormat, String inputDate){
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(inputDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;


    }

}

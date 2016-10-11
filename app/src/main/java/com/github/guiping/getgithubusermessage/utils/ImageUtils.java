package com.github.guiping.getgithubusermessage.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by baoxing on 16/10/10.
 */

public class ImageUtils {
    private ImageUtils() {
    }

    public static void showImage(Context context, ImageView img, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .crossFade()
                .into(img);


    }
}

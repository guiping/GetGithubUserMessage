package com.github.guiping.getgithubusermessage.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by guiping on 16/10/10.
 * Activity 之间跳转工具类
 */

public class ActivityUtils {
    private ActivityUtils() {
    }

    /**
     * @param activity 当前Activity
     * @param t        需要跳转的类
     * @param isFinish 是否销毁当前类
     */
    public static void startActivity(Activity activity, Class t, boolean isFinish) {
        startActivity(activity, t, isFinish);
    }

    /**
     * @param activity
     * @param t
     * @param bun      传递参数
     * @param isFinish
     */
    public static void startActivity(Activity activity, Class t, Bundle bun, boolean isFinish) {
        Intent intent = new Intent(activity, t);
        if (bun != null) {
            intent.putExtras(bun);
        }

        activity.startActivity(intent);

        if (isFinish) {
            activity.finish();
        }
    }

}

package com.github.guiping.getgithubusermessage.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



/**
 * Created by guiping on 16/10/10.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Context mContext;
    public String TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayoutId());
        mContext = this;
        TAG = this.getClass().getName();
    }

    /**
     * 获取当前上下文的Context对象
     * @return
     */
    public Context getContext(){
        return mContext;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public abstract int getViewLayoutId();

    /***
     * 简化findViewById
     * @param viewId view的id
     * @param <T>
     * @return
     */
    public  <T extends View>  T $(int viewId) {
        return (T) super.findViewById(viewId);
    }

}


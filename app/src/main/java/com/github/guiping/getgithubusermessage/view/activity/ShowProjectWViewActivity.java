package com.github.guiping.getgithubusermessage.view.activity;

import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.github.guiping.getgithubusermessage.R;
import com.github.guiping.getgithubusermessage.utils.AppUtils;
import com.github.guiping.getgithubusermessage.view.BaseActivity;
import com.github.guiping.getgithubusermessage.view.activity.IView.ShowProjectWvView;
import com.github.guiping.getgithubusermessage.view.presenter.ShowProjectWvPresenter;

public class ShowProjectWViewActivity extends BaseActivity implements ShowProjectWvView {
    private WebView mWebView;
    private ContentLoadingProgressBar mProgressBar;
    private ShowProjectWvPresenter mShowProjectWvPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        $(R.id.tv_appbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mWebView = $(R.id.webview_show_message);
        mProgressBar = $(R.id.progress_bar);
    }

    private void initData() {
        Bundle bun = getIntent().getExtras();
        String webUrl = "https://www.github.com/guiping";  //设置成自己默认的主页。
        if (bun != null) {
            webUrl = bun.getString("webUrl", "https://www.github.com/guiping");

        }
        mShowProjectWvPresenter = new ShowProjectWvPresenter(this);
        mShowProjectWvPresenter.setWebViewSettings(mWebView, webUrl);
    }


    @Override
    public int getViewLayoutId() {
        return R.layout.activity_show_project_wview;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
        if (mShowProjectWvPresenter != null) {
            mShowProjectWvPresenter.onResReplease();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        if (mShowProjectWvPresenter != null) {
            mShowProjectWvPresenter.onResReplease();
        }
    }

    @Override
    public void showProgressBar(int progress) {
        if (mProgressBar == null) {
            return;
        }
        if (progress == 100) {
            mProgressBar.setVisibility(View.GONE);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(progress);
        }
    }

    @Override
    public void openFail() {

    }
}

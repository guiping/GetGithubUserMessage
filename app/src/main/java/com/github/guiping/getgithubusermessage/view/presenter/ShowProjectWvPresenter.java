package com.github.guiping.getgithubusermessage.view.presenter;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.guiping.getgithubusermessage.view.activity.IView.ShowProjectWvView;

/**
 * Created by guiping on 16/10/10.
 */

public class ShowProjectWvPresenter extends BasePresenter {
    private ShowProjectWvView mShowProjectView;

    public ShowProjectWvPresenter(ShowProjectWvView showProjectWvView) {
        this.mShowProjectView = showProjectWvView;

    }

    public void setWebViewSettings(WebView webView, String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  //允许加载js脚本
        webSettings.setLoadWithOverviewMode(true); //设置WebView 宽度是否根据屏幕的宽度 自动放大缩小
        webSettings.setAppCacheEnabled(true);   //设置打开缓存
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);   //设置WebView是否根据手势放大缩小
        webView.setWebChromeClient(new ChromerClient());
        webView.setWebViewClient(new WebClient());
        webView.loadUrl(url);
    }

    private class ChromerClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {   //WebView加载页面的当前进展情况。      newProgress当前加载进度
            super.onProgressChanged(view, newProgress);
            if (mShowProjectView != null) {
                mShowProjectView.showProgressBar(newProgress);
            }
        }

    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onResReplease() {
        if (mShowProjectView != null) {
            mShowProjectView = null;
        }
    }
}

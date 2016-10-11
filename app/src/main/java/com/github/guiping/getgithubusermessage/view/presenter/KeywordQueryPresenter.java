package com.github.guiping.getgithubusermessage.view.presenter;

import com.github.guiping.getgithubusermessage.apiutils.ApiClient;
import com.github.guiping.getgithubusermessage.model.UserListEntity;
import com.github.guiping.getgithubusermessage.view.activity.IView.KeywordView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by baoxing on 16/10/10.
 */

public class KeywordQueryPresenter extends BasePresenter{
    private KeywordView mKeywordView;

    public KeywordQueryPresenter(KeywordView keywordView) {
        this.mKeywordView = keywordView;
    }

    public void getKeyword(String keyword) {
        //TODO 取消之前的请求 开始新的请求
        ApiClient.getAPiDataRetrofit().getKeywordQueryData(keyword).enqueue(new Callback<UserListEntity>() {
            @Override
            public void onResponse(Call<UserListEntity> call, Response<UserListEntity> response) {
                if (response.isSuccessful()) {
                    if (mKeywordView != null) {
                        mKeywordView.onSearchSucess(response.body().getItems());
                    }
                } else {
                    if (mKeywordView != null) {
                        mKeywordView.onSearchFail("数据获取错误.");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserListEntity> call, Throwable t) {
                if (mKeywordView != null) {
                   mKeywordView.onSearchFail("网络链接错误,请检查您的网络链接.");
                }
            }
        });

    }

    @Override
   public void onResReplease() {
        if(mKeywordView != null ) {
            mKeywordView = null;
        }
    }
}

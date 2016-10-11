package com.github.guiping.getgithubusermessage.view.presenter;

import android.content.Context;

import com.github.guiping.getgithubusermessage.apiutils.ApiClient;
import com.github.guiping.getgithubusermessage.model.UserEntity;
import com.github.guiping.getgithubusermessage.model.UserSubscriptionsEntity;
import com.github.guiping.getgithubusermessage.view.activity.IView.UserDetailMsgView;
import com.github.guiping.getgithubusermessage.view.activity.IView.UserSubscriptView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guiping on 16/10/10.
 */

public class UserDetailPresenter extends  BasePresenter{
    private Context mContext;
    private UserDetailMsgView mUserDetailMsgView;
    private UserSubscriptView mUserSubscriptView;

    public UserDetailPresenter(Context context, UserDetailMsgView userDetailMsgView, UserSubscriptView userSubscriptView) {
        this.mContext = context;
        this.mUserDetailMsgView = userDetailMsgView;
        this.mUserSubscriptView = userSubscriptView;
    }

    /**
     * 请求用户详细信息
     *
     * @param userName
     */
    public void getUserDetailMsg(String userName) {
        ApiClient.getAPiDataRetrofit().getUserMeg(userName).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()) {
                    mUserDetailMsgView.getDetailMsgSucess(response.body());
                } else {
                    if (mUserDetailMsgView != null) {
                        mUserDetailMsgView.getDetailMsgFail("数据请求错误...");
                    }
                }

            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                if (mUserDetailMsgView != null) {
                    mUserDetailMsgView.getDetailMsgFail("网络链接异常,请检查您的网络链接...");
                }
            }
        });
    }

    /***
     * 获取用户订阅信息
     *
     * @param userName
     */
    public void getUserSubscriptData(String userName) {
        ApiClient.getAPiDataRetrofit().getUserSubscript(userName).enqueue(new Callback<ArrayList<UserSubscriptionsEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<UserSubscriptionsEntity>> call, Response<ArrayList<UserSubscriptionsEntity>> response) {
                if (response.isSuccessful()) {
                    mUserSubscriptView.getUserSubSucess(response.body());
                } else {
                    if (mUserSubscriptView != null) {
                        mUserSubscriptView.getUserSubFail("数据请求错误...");
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserSubscriptionsEntity>> call, Throwable t) {
                if (mUserSubscriptView != null) {
                    mUserSubscriptView.getUserSubFail("网络链接异常,请检查您的网络链接...");
                }

            }
        });
    }

    @Override
   public void onResReplease() {
        if(mUserDetailMsgView != null ) {
            mUserDetailMsgView = null;
        }
        if(mUserSubscriptView != null) {
            mUserSubscriptView = null;
        }
    }
}

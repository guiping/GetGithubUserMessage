package com.github.guiping.getgithubusermessage.apiutils;


import com.github.guiping.getgithubusermessage.model.UserEntity;
import com.github.guiping.getgithubusermessage.model.UserListEntity;
import com.github.guiping.getgithubusermessage.model.UserReposEntity;
import com.github.guiping.getgithubusermessage.model.UserSubscriptionsEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by guiping on 16/10/10.
 */

public interface ApiDataRetrofit {
    /**
     * 根据关键字查询数据
     */
    @GET("search/users")
    Call<UserListEntity> getKeywordQueryData(@Query("q") String keyword);

    /**
     * 查询用户偏好语言
     */
    @GET("users/{name}/repos")
    Call<UserReposEntity> getUserRepos(@Path("name") String userName);

    /***
     * 获取用户详细信息
     */
    @GET("users/{name}")
    Call<UserEntity> getUserMeg(@Path("name") String userName);

    /***
     * 获取用户
     */
    @GET("users/{name}/subscriptions")
    Call<ArrayList<UserSubscriptionsEntity>> getUserSubscript(@Path("name") String userName);
}

package com.github.guiping.getgithubusermessage.view.activity.IView;

import com.github.guiping.getgithubusermessage.model.UserListEntity;

import java.util.List;

/**
 * Created by baoxing on 16/10/10.
 */

public interface KeywordView {
    //TODO 鉴于输入就立刻查询 不是手点击查询 如果谈对话框影响用户体验
//    void onShowLoading();
//    void onHideLoding();

   void onSearchSucess(List<UserListEntity.ItemsBean> list);
    void onSearchFail(String failMsg);

}

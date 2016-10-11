package com.github.guiping.getgithubusermessage.view.activity.IView;

import com.github.guiping.getgithubusermessage.model.UserSubscriptionsEntity;

import java.util.ArrayList;

/**
 * Created by guiping on 16/10/10.
 */

public interface UserSubscriptView extends IBaseView {
    void getUserSubSucess(ArrayList<UserSubscriptionsEntity> list);

    void getUserSubFail(String failStr);
}

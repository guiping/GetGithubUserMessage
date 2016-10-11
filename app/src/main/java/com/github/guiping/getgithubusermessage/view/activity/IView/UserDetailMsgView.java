package com.github.guiping.getgithubusermessage.view.activity.IView;

import com.github.guiping.getgithubusermessage.model.UserEntity;

/**
 * Created by baoxing on 16/10/11.
 */

public interface UserDetailMsgView extends IBaseView {
    void getDetailMsgSucess(UserEntity userEntity);

    void getDetailMsgFail(String failString);

}

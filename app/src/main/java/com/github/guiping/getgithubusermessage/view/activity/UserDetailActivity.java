package com.github.guiping.getgithubusermessage.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.guiping.getgithubusermessage.R;
import com.github.guiping.getgithubusermessage.model.UserEntity;
import com.github.guiping.getgithubusermessage.model.UserSubscriptionsEntity;
import com.github.guiping.getgithubusermessage.utils.ActivityUtils;
import com.github.guiping.getgithubusermessage.utils.AppUtils;
import com.github.guiping.getgithubusermessage.utils.ImageUtils;
import com.github.guiping.getgithubusermessage.view.BaseActivity;
import com.github.guiping.getgithubusermessage.view.activity.IView.UserDetailMsgView;
import com.github.guiping.getgithubusermessage.view.activity.IView.UserSubscriptView;
import com.github.guiping.getgithubusermessage.view.adapter.UsersSubscriptListAdapter;
import com.github.guiping.getgithubusermessage.view.presenter.UserDetailPresenter;

import java.util.ArrayList;

public class UserDetailActivity extends BaseActivity implements UserDetailMsgView, UserSubscriptView {

    private ImageView mImgUserDetailPhoto;
    private TextView mTvUserDetailName;
    private TextView mTvFollowers;
    private TextView mTvFollowing;
    private RecyclerView mRecyclerViewItem;
    private String userName;
    private ProgressDialog mProgressDialog;
    private UserDetailPresenter mUserDetailPresenter;
    private UsersSubscriptListAdapter mUserSubAdapter;
    private ArrayList<UserSubscriptionsEntity> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }


    private void initView() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mImgUserDetailPhoto = $(R.id.img_user_detail_photo);
        mTvUserDetailName = $(R.id.tv_user_name);
        mTvFollowers = $(R.id.tv_followers);
        mTvFollowing = $(R.id.tv_following);
        $(R.id.tv_appbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRecyclerViewItem = $(R.id.recyclerview_user_item);
        mUserDetailPresenter = new UserDetailPresenter(getContext(), this, this);

        mList = new ArrayList<>();
        mUserSubAdapter = new UsersSubscriptListAdapter(getContext(), mList);
        mRecyclerViewItem.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewItem.setAdapter(mUserSubAdapter);
        mUserSubAdapter.setOnItemClickListener(new UsersSubscriptListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserSubscriptionsEntity userSubscriptionsEntity, int position) {
                Bundle bun = new Bundle();
                bun.putString("webUrl", userSubscriptionsEntity.getHtml_url());
                ActivityUtils.startActivity(UserDetailActivity.this, ShowProjectWViewActivity.class, bun, false);
            }
        });
    }

    private void initData() {
        Bundle bun = getIntent().getExtras();
        if (bun != null) {
            userName = bun.getString("userName");
        }
        if (AppUtils.isEmpty(userName)) {
            Snackbar.make(mRecyclerViewItem, "数据传递错误...", Snackbar.LENGTH_LONG).show();
            return;
        }
        //加载用户的详细数据
        mUserDetailPresenter.getUserDetailMsg(userName);
        //加载用户订阅消息
        mUserDetailPresenter.getUserSubscriptData(userName);

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void getDetailMsgSucess(UserEntity userEntity) {
        ImageUtils.showImage(getContext(), mImgUserDetailPhoto, userEntity.getAvatar_url());
        mTvUserDetailName.setText(userEntity.getLogin());
        mTvFollowing.setText(userEntity.getFollowing() + "");
        mTvFollowers.setText(userEntity.getFollowers() + "");
    }

    @Override
    public void getDetailMsgFail(String failString) {
        Snackbar.make(mRecyclerViewItem, failString, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void getUserSubSucess(ArrayList<UserSubscriptionsEntity> list) {
        Log.e(TAG, "--->>>" + list.size());
        if (mList != null && list.size() > 0) {
            mList.clear();
            mList.addAll(list);
            mUserSubAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getUserSubFail(String failStr) {
        Snackbar.make(mRecyclerViewItem, failStr, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onShowDialog() {
        if (mProgressDialog == null || !mProgressDialog.isShowing()) {
            mProgressDialog = ProgressDialog.show(getContext(), "", "正在加载,请稍后...");
            mProgressDialog.show();
        }
    }

    @Override
    public void onHideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUserDetailPresenter != null) {
            mUserDetailPresenter.onResReplease();
        }
    }
}

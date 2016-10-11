package com.github.guiping.getgithubusermessage.view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.github.guiping.getgithubusermessage.R;
import com.github.guiping.getgithubusermessage.model.UserListEntity;
import com.github.guiping.getgithubusermessage.utils.ActivityUtils;
import com.github.guiping.getgithubusermessage.utils.AppUtils;
import com.github.guiping.getgithubusermessage.view.adapter.UsersListAdapter;
import com.github.guiping.getgithubusermessage.view.presenter.KeywordQueryPresenter;
import com.github.guiping.getgithubusermessage.view.BaseActivity;
import com.github.guiping.getgithubusermessage.view.activity.IView.KeywordView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements KeywordView {
    //
    EditText mEditInputSearchMsg;
    RecyclerView mRecyclerviewUserList;
    private KeywordQueryPresenter mKeywordQueryP;
    private UsersListAdapter mUserListAdapter;
    private ArrayList<UserListEntity.ItemsBean> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mEditInputSearchMsg = $(R.id.edit_input_search_msg);
        mRecyclerviewUserList = $(R.id.recyclerview_user_list);
        mKeywordQueryP = new KeywordQueryPresenter(this);
        mEditInputSearchMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "beforeTextCHange");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e(TAG, "afterTextChanged---:" + editable);

                //TODO 调用网络查询 查询对应的数据
                String keyword = editable.toString();
                if(AppUtils.isEmpty(keyword)){
                    return;
                }
                mKeywordQueryP.getKeyword(keyword);
            }
        });
        mList = new ArrayList<>();
        mUserListAdapter = new UsersListAdapter(getContext(), mList);
        mRecyclerviewUserList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerviewUserList.setAdapter(mUserListAdapter);
        mUserListAdapter.setOnItemClickListener(new UsersListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserListEntity.ItemsBean userEntity, int position) {
                //TODO 跳转到用户详情界面
                Bundle bundle = new Bundle();
                bundle.putString("userName",userEntity.getLogin());
                ActivityUtils.startActivity(MainActivity.this, UserDetailActivity.class, bundle, false);
            }
        });
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public void onSearchSucess(List<UserListEntity.ItemsBean> list) {
        mList.clear();
        mList.addAll(list);
        mUserListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchFail(String failMsg) {
        Snackbar.make(mRecyclerviewUserList, failMsg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mKeywordQueryP != null ) {
            mKeywordQueryP.onResReplease();
        }
    }
}

package com.github.guiping.getgithubusermessage.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.guiping.getgithubusermessage.R;
import com.github.guiping.getgithubusermessage.model.UserListEntity;
import com.github.guiping.getgithubusermessage.model.UserSubscriptionsEntity;
import com.github.guiping.getgithubusermessage.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by guiping on 16/10/10.
 */

public class UsersSubscriptListAdapter extends RecyclerView.Adapter<UsersSubscriptListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<UserSubscriptionsEntity> mList;
    private LayoutInflater inflater;


    public UsersSubscriptListAdapter(Context context, ArrayList<UserSubscriptionsEntity> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_user_subscript, parent, false
        ));
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final UserSubscriptionsEntity use = mList.get(position);

        holder.mTvSubscriptName.setText(use.getName());
        holder.mTvSubscriptStar.setText("star:" + use.getStargazers_count());
        holder.mTvSubscriptForks.setText("forks:" + use.getForks_count());
        holder.mTvDesctipt.setText("描述:" + use.getDescription());
        holder.mCarViewSubscript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(use, position);
                }
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected CardView mCarViewSubscript;
        protected TextView mTvSubscriptName;
        protected TextView mTvSubscriptStar;
        protected TextView mTvSubscriptForks;
        protected TextView mTvDesctipt;


        public ViewHolder(View itemView) {
            super(itemView);
            mCarViewSubscript = $(itemView, R.id.cardview_subscript);
            mTvSubscriptName = $(itemView, R.id.tv_sbscript_name);
            mTvSubscriptStar = $(itemView, R.id.tv_start);
            mTvSubscriptForks = $(itemView, R.id.tv_forks);
            mTvDesctipt = $(itemView, R.id.tv_descript);
        }

        public <T extends View> T $(View mView, int viewId) {
            return (T) mView.findViewById(viewId);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserSubscriptionsEntity userSubscriptionsEntity, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}

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
import com.github.guiping.getgithubusermessage.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by guiping on 16/10/10.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<UserListEntity.ItemsBean> mList;
    private LayoutInflater inflater;


    public UsersListAdapter(Context context, ArrayList<UserListEntity.ItemsBean> list) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_user_msg, parent, false
        ));
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final UserListEntity.ItemsBean userMsg = mList.get(position);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        holder.mCardView.setBackgroundColor(Color.argb(204, red, green, blue));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(userMsg, position);
                }
            }
        });

        ImageUtils.showImage(mContext, holder.mImgUserPhoto, userMsg.getAvatar_url());
        holder.mTvUserName.setText("" + userMsg.getLogin());
//        holder.mTvUserLike.setText("Like:" + userMsg.getType());
        holder.mTvUserGithub.setText("Github:" + userMsg.getHtml_url());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView mImgUserPhoto;
        protected TextView mTvUserName;
        protected TextView mTvUserLike;
        protected TextView mTvUserGithub;
        private CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImgUserPhoto = $(itemView, R.id.img_user_photo);
            mTvUserName = $(itemView, R.id.tv_user_name);
            mTvUserLike = $(itemView, R.id.tv_user_like);
            mTvUserGithub = $(itemView, R.id.tv_user_github);
            mCardView = $(itemView, R.id.cardview);

        }

        public <T extends View> T $(View mView, int viewId) {
            return (T) mView.findViewById(viewId);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserListEntity.ItemsBean userEntity, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}

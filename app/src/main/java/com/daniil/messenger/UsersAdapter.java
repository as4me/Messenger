package com.daniil.messenger;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {

    private Context context;
    private List<User> userResults;

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView userName;
        ImageView profilePic;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            userName = mView.findViewById(R.id.userName);
            profilePic = mView.findViewById(R.id.userProfile);
        }
    }
    public UsersAdapter(Context context, List<User> itemResults){
        this.context = context;
        this.userResults = itemResults;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        Log.d("email",userResults.get(position).email);
        holder.userName.setText(userResults.get(position).getNick());
        if(!userResults.get(position).getLinkPhoto().equals("default")){
            Glide.with(context).load(userResults.get(position).getLinkPhoto()).into(holder.profilePic);
        }
    }

    @Override
    public int getItemCount() {
        return userResults.size();
    }
}

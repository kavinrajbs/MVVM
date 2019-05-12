package com.kavinraj.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kavinraj.model.NicePlace;
import com.kavinraj.mvvm.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private List<NicePlace> mnicePlaces = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(List<NicePlace> mnicePlaces, Context context) {
        this.mnicePlaces = mnicePlaces;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: Called");
        Glide.with(mContext)
                .asBitmap()
                .load(mnicePlaces.get(position).getImageUrl())
                .into(holder.image);
        holder.imageName.setText(mnicePlaces.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mnicePlaces.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView imageName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            imageName =itemView.findViewById(R.id.imageName);
        }
    }
}

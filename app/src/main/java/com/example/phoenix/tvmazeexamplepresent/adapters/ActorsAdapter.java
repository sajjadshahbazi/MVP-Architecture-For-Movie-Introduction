package com.example.phoenix.tvmazeexamplepresent.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.phoenix.tvmazeexamplepresent.R;
import com.example.phoenix.tvmazeexamplepresent.models.film_detail.Embedded;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Sajjad on 13-08-2017.
 */

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewHolder> {
    private Embedded data;
    private Context context;

    public ActorsAdapter(Context context, Embedded data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ActorsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        ButterKnife.bind(this,view);
        return new ActorsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorsAdapter.ViewHolder holder, int position) {
        holder.name.setText(data.getCast().get(position).getPerson().getName());
        Glide.with(context)
                .load(data.getCast().get(position).getPerson().getImage().getMedium())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.getCast().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = ButterKnife.findById(itemView, R.id.name);
            image = ButterKnife.findById(itemView, R.id.image);
        }

    }

}

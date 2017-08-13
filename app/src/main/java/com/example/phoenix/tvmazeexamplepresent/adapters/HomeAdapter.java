package com.example.phoenix.tvmazeexamplepresent.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.phoenix.tvmazeexamplepresent.R;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Phoenix on 8/11/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private OnItemClickListener listener;
    private List<FilmsList> data;
    private Context context;


    public HomeAdapter(Context context, List<FilmsList> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        ButterKnife.bind(this,view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.click(data.get(position), listener);
        Glide.with(context)
                .load(data.get(position).getImage().getMedium())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(FilmsList Item);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = ButterKnife.findById(itemView, R.id.name);
            image = ButterKnife.findById(itemView, R.id.image);
        }

        public void click(final FilmsList filmsList, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(filmsList);
                }
            });
        }


    }





}

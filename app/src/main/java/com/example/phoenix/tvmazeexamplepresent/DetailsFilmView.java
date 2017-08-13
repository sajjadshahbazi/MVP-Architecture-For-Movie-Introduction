package com.example.phoenix.tvmazeexamplepresent;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.phoenix.tvmazeexamplepresent.adapters.ActorsAdapter;
import com.example.phoenix.tvmazeexamplepresent.adapters.HomeAdapter;
import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Phoenix on 13-08-2017.
 */

public abstract class DetailsFilmView extends RelativeLayout {

    @BindView(R.id.originalImage)
    ImageView originalImage;
    @BindView(R.id.ValueName)
    TextView valueName;
    @BindView(R.id.ValueGenres)
    TextView valueGenres;
    @BindView(R.id.ValueRating)
    RatingBar valueRating;
    @BindView(R.id.ValueSummery)
    TextView valueSummery;
    @BindView(R.id.closeDetailView)
    Button closeDetailView;
    @BindView(R.id.listActors)
    RecyclerView listActors;

    Context context;
    FilmDetails filmDetails;


    @OnClick(R.id.closeDetailView)
    public void click_close(){
        OnClosedClick();
    }

    public DetailsFilmView(Context context,FilmDetails filmDetails) {
        super(context);
        this.context=context;
        this.filmDetails=filmDetails;
        init();
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.film_details, this);
        ButterKnife.bind(this);
        valueName.setText(filmDetails.getName());
        String strGenres=filmDetails.getGenres().toString().replace("[","").replace("]","");
        valueGenres.setText(strGenres);
        valueRating.setRating(Float.parseFloat((filmDetails.getRating().getAverage()).toString())/2);
        valueRating.setEnabled(false);
        valueSummery.setText(Html.fromHtml(filmDetails.getSummary()));
        Glide.with(context)
                .load(filmDetails.getImage().getOriginal())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        progressBar.setVisibility(VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        progressBar.setVisibility(VISIBLE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(originalImage);
        listActors.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
        listActors.setHasFixedSize(true);
        ActorsAdapter actorsAdapter=new ActorsAdapter(context,filmDetails.getEmbedded());
        listActors.setAdapter(actorsAdapter);
    }
    public abstract void OnClosedClick();
}

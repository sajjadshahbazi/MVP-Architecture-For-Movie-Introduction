package com.example.phoenix.tvmazeexamplepresent.Activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.phoenix.tvmazeexamplepresent.Components.ComponentHomePresenter;
import com.example.phoenix.tvmazeexamplepresent.Components.DaggerComponentHomePresenter_HomeComponent;
import com.example.phoenix.tvmazeexamplepresent.DetailsFilmView;
import com.example.phoenix.tvmazeexamplepresent.Navigate;
import com.example.phoenix.tvmazeexamplepresent.R;
import com.example.phoenix.tvmazeexamplepresent.adapters.HomeAdapter;
import com.example.phoenix.tvmazeexamplepresent.interfaces.HomeView;
import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;
import com.example.phoenix.tvmazeexamplepresent.modules.HomeModules;
import com.example.phoenix.tvmazeexamplepresent.pressenters.HomePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Phoenix on 8/11/2017.
 */

public class HomeActivity extends AppCompatActivity implements HomeView {
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.retryGetList)
    ImageButton retryGetList;
    @BindView(R.id.instantContainer)
    FrameLayout instantContainer;

    HomePresenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView();
        ComponentHomePresenter.HomeComponent component = DaggerComponentHomePresenter_HomeComponent.builder().homeModules(new HomeModules(this)).build();
        homePresenter = component.presenterProvider();
        homePresenter.GetFilmsList();
    }



    @OnClick
    public void LoadMoreView(){

    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
        retryGetList.setVisibility(View.GONE);
        instantContainer.setVisibility(View.GONE);
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        list.setLayoutManager(new GridLayoutManager(this, 4));
        list.setHasFixedSize(true);
    }

    @Override
    public void removeWait() {
        list.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        retryGetList.setVisibility(View.GONE);
        instantContainer.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        progressBar.setVisibility(View.GONE);
        retryGetList.setVisibility(View.VISIBLE);
        instantContainer.setVisibility(View.GONE);
    }

    @Override
    public void getFilmList(List<FilmsList> filmsLists) {
        retryGetList.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        instantContainer.setVisibility(View.GONE);
        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), filmsLists,
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(FilmsList item) {
                        if (homePresenter!=null){
                            homePresenter.GetDetailsFilm(item.getId(),"cast");
                        }else {
                            Toast.makeText(getApplicationContext(),"Object is Null",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        list.setAdapter(adapter);
    }

    @Override
    public void showDetailsView(FilmDetails filmDetails) {
        instantContainer.setVisibility(View.VISIBLE);
        instantContainer.removeAllViews();
        DetailsFilmView detailsFilmView=new DetailsFilmView(this,filmDetails) {
            @Override
            public void OnClosedClick() {
                deposeDetailsView();
            }
        };
        instantContainer.addView(detailsFilmView);
    }
    @Override
    public void deposeDetailsView() {
        instantContainer.removeAllViews();
        instantContainer.setVisibility(View.GONE);
    }
    @Override
    public void onFailureDetail(String appErrorMessage) {
        Toast.makeText(getApplicationContext(),"Error Get Details Film : "+appErrorMessage,Toast.LENGTH_LONG).show();
        instantContainer.setVisibility(View.GONE);
    }
}

package com.example.phoenix.tvmazeexamplepresent.pressenters;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Long2;
import android.util.Log;
import android.widget.Toast;

import com.example.phoenix.tvmazeexamplepresent.interfaces.HomeView;
import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;
import com.example.phoenix.tvmazeexamplepresent.server_conector.GetDataFromServer;
import com.example.phoenix.tvmazeexamplepresent.tools.InternetConnectionState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Phoenix on 8/11/2017.
 */

public class HomePresenter {
    public Callback<FilmDetails> detailsCallback;
    public Callback<List<FilmsList>> listCallback;
    public HomeView homeView;

    @Inject
    public HomePresenter(HomeView hView) {
        this.homeView=hView;
    }

    public void GetFilmsList() {
        listCallback = new Callback<List<FilmsList>>() {
            @Override
            public void onResponse(Call<List<FilmsList>> call, Response<List<FilmsList>> response) {
                if (response.isSuccessful()){
                    homeView.getFilmList(response.body());
                } else {homeView.onFailure(response.errorBody().toString());}
            }

            @Override
            public void onFailure(Call<List<FilmsList>> call, Throwable t) {
                homeView.onFailure(t.getMessage());
            }
        };
        new GetDataFromServer().filmsListApi(listCallback);
    }

    public void GetDetailsFilm(Integer id,String cast) {
        detailsCallback = new Callback<FilmDetails>() {
            @Override
            public void onResponse(Call<FilmDetails> call, Response<FilmDetails> response) {
                if (response.isSuccessful()){
                    homeView.showDetailsView(response.body());
                } else {
                    homeView.onFailureDetail(response.message());
                }
            }

            @Override
            public void onFailure(Call<FilmDetails> call, Throwable t) {
                homeView.onFailureDetail(t.getMessage());
            }
        };
        new GetDataFromServer().filmDetailApi(detailsCallback,id,cast);
    }




    //    @Inject
//    public HomePresenter(Context context) {
//        this.context = context;
//    }


}

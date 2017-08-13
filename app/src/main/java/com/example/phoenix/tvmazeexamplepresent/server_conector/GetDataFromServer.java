package com.example.phoenix.tvmazeexamplepresent.server_conector;

import com.example.phoenix.tvmazeexamplepresent.DetailsFilmView;
import com.example.phoenix.tvmazeexamplepresent.Values;
import com.example.phoenix.tvmazeexamplepresent.interfaces.APIs;
import com.example.phoenix.tvmazeexamplepresent.interfaces.GetJSONFilmList;
import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Phoenix on 8/11/2017.
 */

public class GetDataFromServer implements APIs {
    Retrofit retrofit;

    public GetDataFromServer() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Values.BaseURL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .client(okHttpClient)
                .build();
    }

    @Override
    public void filmsListApi(Callback<List<FilmsList>> listCallback) {
        retrofit.create(GetJSONFilmList.class).FilmsList().enqueue(listCallback);
    }

    @Override
    public void filmDetailApi(Callback<FilmDetails> detailsFilmCallback, Integer id, String cast) {
        retrofit.create(GetJSONFilmList.class).DetailFilm(id,cast).enqueue(detailsFilmCallback);
    }


}

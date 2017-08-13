package com.example.phoenix.tvmazeexamplepresent.interfaces;

import com.example.phoenix.tvmazeexamplepresent.DetailsFilmView;
import com.example.phoenix.tvmazeexamplepresent.Values;
import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Phoenix on 8/11/2017.
 */

public interface GetJSONFilmList {
    @GET(Values.ShowFilm)
    Call<List<FilmsList>> FilmsList();
    @GET(Values.ShowFilm+"/{id}")
    Call<FilmDetails> DetailFilm(@Path("id") Integer id, @Query("embed") String cast);
}

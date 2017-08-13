package com.example.phoenix.tvmazeexamplepresent.interfaces;

import com.example.phoenix.tvmazeexamplepresent.DetailsFilmView;
import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Phoenix on 8/11/2017.
 */

public interface APIs {
    public void filmsListApi(Callback<List<FilmsList>> listCallback);
    public void filmDetailApi(Callback<FilmDetails> detailsFilmCallback, Integer id, String cast);
}

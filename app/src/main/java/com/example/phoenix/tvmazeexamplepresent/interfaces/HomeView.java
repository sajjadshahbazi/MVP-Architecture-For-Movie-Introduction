package com.example.phoenix.tvmazeexamplepresent.interfaces;

import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;

import java.util.List;

/**
 * Created by Phoenix on 8/11/2017.
 */

public interface HomeView {
    void showWait();
    void removeWait();
    void onFailure(String appErrorMessage);
    void getFilmList(List<FilmsList> filmsLists);
    void showDetailsView(FilmDetails filmDetails);
    void deposeDetailsView();
    void onFailureDetail(String appErrorMessage);
}

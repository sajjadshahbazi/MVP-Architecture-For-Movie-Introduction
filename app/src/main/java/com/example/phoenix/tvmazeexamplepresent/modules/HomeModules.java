package com.example.phoenix.tvmazeexamplepresent.modules;

import android.content.Context;

import com.example.phoenix.tvmazeexamplepresent.interfaces.HomeView;
import com.example.phoenix.tvmazeexamplepresent.pressenters.HomePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Phoenix on 8/11/2017.
 */
@Module
public class HomeModules {

    HomeView homeView;
    public HomeModules(HomeView homeView){
        this.homeView=homeView;
    }

    @Singleton
    @Provides
    HomePresenter PresenterProvider(){
       return new HomePresenter(homeView);
    }

}

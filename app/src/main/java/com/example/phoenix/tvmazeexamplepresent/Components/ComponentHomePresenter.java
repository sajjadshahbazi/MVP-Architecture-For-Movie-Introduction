package com.example.phoenix.tvmazeexamplepresent.Components;

import com.example.phoenix.tvmazeexamplepresent.interfaces.HomeView;
import com.example.phoenix.tvmazeexamplepresent.modules.HomeModules;
import com.example.phoenix.tvmazeexamplepresent.pressenters.HomePresenter;

import javax.inject.Singleton;

/**
 * Created by Phoenix on 13-08-2017.
 */

public class ComponentHomePresenter {
    @Singleton
    @dagger.Component(modules = {HomeModules.class})
    public interface HomeComponent {
        HomePresenter presenterProvider();
    }
}

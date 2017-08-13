package com.example.phoenix.tvmazeexamplepresent.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.phoenix.tvmazeexamplepresent.Navigate;
import com.example.phoenix.tvmazeexamplepresent.R;
import com.example.phoenix.tvmazeexamplepresent.interfaces.Navigator;
import com.example.phoenix.tvmazeexamplepresent.modules.HomeModules;
import com.example.phoenix.tvmazeexamplepresent.pressenters.HomePresenter;
import com.example.phoenix.tvmazeexamplepresent.tools.InternetConnectionState;

public class MainActivity extends AppCompatActivity {
    Navigate navigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigate=new Navigate(this);

        if (checkInternetConnection()){
            navigate.navigateToHomeAndFinish();
        }else {
            navigate.navigateToInternatFailAndFinish();
        }
    }
    public boolean checkInternetConnection() {
        return new InternetConnectionState(this).isNetworkAvailable();
    }
}

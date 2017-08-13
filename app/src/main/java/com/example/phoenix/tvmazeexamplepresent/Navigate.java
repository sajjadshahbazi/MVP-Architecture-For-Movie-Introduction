package com.example.phoenix.tvmazeexamplepresent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.phoenix.tvmazeexamplepresent.Activities.HomeActivity;
import com.example.phoenix.tvmazeexamplepresent.Activities.InternetConnectionFailActivity;
import com.example.phoenix.tvmazeexamplepresent.interfaces.Navigator;

/**
 * Created by Phoenix on 8/11/2017.
 */

public class Navigate implements Navigator {
    private Context context;

    public Navigate(Context context) {
        this.context = context;
    }

    public void navigateToHomeAndFinish() {
        Home();
        if (Activity.class.isAssignableFrom(context.getClass())) {
            Activity activity = (Activity) context;
            activity.finish();
        }
    }

    public void navigateToInternatFailAndFinish() {
        InternetConnectionFail();
        if (Activity.class.isAssignableFrom(context.getClass())) {
            Activity activity = (Activity) context;
            activity.finish();
        }
    }

    @Override
    public void InternetConnectionFail() {
        context.startActivity(new Intent(context, InternetConnectionFailActivity.class));
    }

    @Override
    public void Home() {
        context.startActivity(new Intent(context, HomeActivity.class));
    }
}

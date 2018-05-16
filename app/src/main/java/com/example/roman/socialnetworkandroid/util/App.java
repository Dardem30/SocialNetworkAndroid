package com.example.roman.socialnetworkandroid.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.roman.socialnetworkandroid.data.components.ApplicationComponent;
import com.example.roman.socialnetworkandroid.data.components.DaggerApplicationComponent;
import com.example.roman.socialnetworkandroid.data.module.ContextModule;


/**
 * Created by Roman on 02.05.2018.
 */

public class App extends Application{
    public static SharedPreferences sSharedPreferences;
    private static Context context;
//    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        sSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
//        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
//        applicationComponent.injectApplication(this);
    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

    public static Context getContext() {return App.context;}
//    public static App get(Activity activity){
//        return (App) activity.getApplication();
//    }

//    public ApplicationComponent getApplicationComponent() {
//        return applicationComponent;
//    }
}

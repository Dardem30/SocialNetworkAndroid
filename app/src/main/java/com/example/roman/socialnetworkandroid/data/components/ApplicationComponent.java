package com.example.roman.socialnetworkandroid.data.components;

import android.content.Context;


import com.example.roman.socialnetworkandroid.MainActivity;
import com.example.roman.socialnetworkandroid.MainPageActivity;
import com.example.roman.socialnetworkandroid.data.module.ContextModule;
import com.example.roman.socialnetworkandroid.data.module.RetrofitModule;
import com.example.roman.socialnetworkandroid.data.qualifier.ApplicationContext;
import com.example.roman.socialnetworkandroid.data.scopes.ApplicationScope;
import com.example.roman.socialnetworkandroid.retrofit.APIInterface;
import com.example.roman.socialnetworkandroid.util.App;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MainActivity mainActivity);
    void injectApplication(MainPageActivity mainActivity);
}

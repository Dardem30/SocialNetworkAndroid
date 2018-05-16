package com.example.roman.socialnetworkandroid.data.module;

import android.content.Context;


import com.example.roman.socialnetworkandroid.data.qualifier.ApplicationContext;
import com.example.roman.socialnetworkandroid.data.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}

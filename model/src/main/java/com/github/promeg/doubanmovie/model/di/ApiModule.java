package com.github.promeg.doubanmovie.model.di;

import com.google.gson.Gson;

import com.github.promeg.doubanmovie.model.movie.MovieApi;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.squareup.okhttp.OkHttpClient;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by guyacong on 2015/9/4.
 */
@Module
public class ApiModule {

    @Provides
    OkHttpClient provideOkHttpClient(){
        return OkHttpProvider.getOkHttpClient();
    }

    @Provides
    MovieApi provideMovieApi(Gson gson, OkHttpClient client) {
        return RetrofitProvider.getInstance(gson, client).getRestApi(MovieApi.class);
    }

    @Provides
    StorIOSQLite provideStoreIOSQLite (Context context, Gson gson) {
        return StorIOProvider.getStorIO(context, gson);
    }

    @Provides @Named("io")
    Scheduler provideIoScheduler(){
        return Schedulers.io();
    }
}

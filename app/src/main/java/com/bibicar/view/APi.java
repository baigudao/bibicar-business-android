package com.bibicar.view;

import com.bibicar.bean.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by jackie on 2017/6/16 18:06.
 * QQ : 971060378
 * Used as : xxx
 */
public interface APi {
    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("word/word")
    Call<News> getNews(@Query("num") String num, @Query("page")String page);
}

package com.bibicar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bibicar.R;
import com.bibicar.bean.News;
import com.bibicar.view.APi;
import com.blankj.utilcode.util.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //                setContentView(R.layout.fragment_home);//首页页面
        setContentView(R.layout.fragment_content_customization);//内容定制页面

        Retrofit retrofit = new Retrofit.Builder()
                //使用自定义的mGsonConverterFactory
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://apis.baidu.com/txapi/")
                .build();

        APi mApi = retrofit.create(APi.class);

        Call<News> news = mApi.getNews("1", "10");
        news.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                ToastUtils.showLong(response.body().getNum());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}

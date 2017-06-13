package com.bibicar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bibicar.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //                setContentView(R.layout.fragment_home);//首页页面
        setContentView(R.layout.fragment_content_customization);//内容定制页面
    }
}

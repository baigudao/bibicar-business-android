package com.bibicar;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.bibicar.bean.DaoMaster;
import com.bibicar.bean.DaoSession;
import com.bibicar.fragment.BaseFragment;
import com.blankj.utilcode.util.Utils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by jackie on 2017/6/3 10:03.
 * QQ : 971060378
 * Used as : 应用的基类
 */
public class BaseApplication extends Application {

    private static DaoSession daoSession;
    private static BaseFragment curFragment;

    @Override
    public void onCreate() {
        super.onCreate();

        //AndroidUtilCode库的初始化
        Utils.init(this);

        //LeakCanary初始化
        //        LeakCanary.install(this);

        //配置GreenDao数据库
        configureGreenDao();

        //配置OKHttp
        configureOkHttp();
    }

    private void configureOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 配置GreenDao
     * <p>
     * DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现
     * DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
     * DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API
     */
    private void configureGreenDao() {
        //创建数据库bibicar.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "bibicar_business.db", null);
        //获取可写数据库
        SQLiteDatabase database = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(database);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static void setCurFragment(BaseFragment baseFragment) {
        curFragment = baseFragment;
    }

    public static BaseFragment getCurFragment() {
        return curFragment;
    }
}

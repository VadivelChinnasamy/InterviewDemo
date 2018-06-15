package com.datastructure.activity;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.datastructure.R;
import com.datastructure.Utils.Constant;
import com.datastructure.Utils.TypefaceUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created MyApplication
 */

public class MyApplication extends Application {

    private static Gson gson;
    private static Retrofit mRet;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

    }

    /***
     * Create Instance method to access a API
     * API time out 30 mili sec
     * **/
    public static Retrofit getRequestQueue(Context mctx) {
        mContext = mctx;
        if (mRet == null) {
            try {
                OkHttpClient oky = null;
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build();

                gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").setLenient()
                        .create();

                mRet = new Retrofit.Builder()
                        .baseUrl(Constant.BASEURL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return mRet;
    }

}
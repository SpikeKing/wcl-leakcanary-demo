package me.chunyu.spike.wcl_leakcanary_demo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * 应用
 * <p/>
 * Created by wangchenlong on 16/1/25.
 */
public class DemoApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}

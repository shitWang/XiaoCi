package com.shannon.xiaoci;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Shannon on 2016/4/10.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        init();

    }

    private void init() {

        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(ilc);

    }
}

package com.play.zv.seamountain;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Zv on 2017/06/07.
 */

public class SeaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

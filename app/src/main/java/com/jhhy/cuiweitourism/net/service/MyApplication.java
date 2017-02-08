package com.jhhy.cuiweitourism.net.service;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.xutils.x;

import io.realm.RealmConfiguration;

/**
 * Created by jiahe008 on 2016/9/27.
 */
public class MyApplication extends Application {

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); // 这一步之后, 我们就可以在任何地方使用x.app()来获取Application的实例了.
        x.Ext.setDebug(true); // 是否输出debug日志

        refWatcher  = LeakCanary.install(this);
//        RealmConfiguration cfg = new RealmConfiguration.Builder(getApplicationContext())
//                .name("realname")
//                .schemaVersion(1)
//                .deleteRealmIfMigrationNeeded()
////                .assetFile(getApplicationContext(), "realmname")
//                .build();
//        Realm.setDefaultConfiguration(cfg);
    }
}

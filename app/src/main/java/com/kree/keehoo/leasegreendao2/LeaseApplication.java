package com.kree.keehoo.leasegreendao2;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.kree.keehoo.leasegreendao2.model.DaoMaster;
import com.kree.keehoo.leasegreendao2.model.DaoSession;

/**
 * Created by keehoo on 20.04.2016.
 */
public class LeaseApplication extends Application {
    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "lease-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}


package com.thecodevillage.remindme;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

public class MainApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate(){
        super.onCreate();

        //regular SQLite Database
        DaoMaster.DevOpenHelper helper= new DaoMaster.DevOpenHelper(this,"remind_me",null);
        Database db=helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

}

package com.app.felixchidev.mypokegotool.helper;

import android.content.Context;

import com.app.felixchidev.mypokegotool.DBPokemonDao;
import com.app.felixchidev.mypokegotool.DaoMaster;
import com.app.felixchidev.mypokegotool.DaoSession;

/**
 * Created by FelixChi on 3/9/2016.
 */
public class GDAOHelper {

    private static GDAOHelper instance = null;
    public static GDAOHelper getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new GDAOHelper(context);
        }
        return instance;
    }

    private static final String DB_NAME = "mypokemontool_dao.db";

    private DaoSession daoSession;

    private GDAOHelper(Context context)
    {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        daoSession = daoMaster.newSession();
    }

    public DBPokemonDao getDPPokemonDao()
    {
        return daoSession.getDBPokemonDao();
    }
    public void clearDB()
    {
        getDPPokemonDao().deleteAll();
    }
}
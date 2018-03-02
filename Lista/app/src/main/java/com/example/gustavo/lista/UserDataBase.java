package com.example.gustavo.lista;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import static com.example.gustavo.lista.UserDataBase.DATABASE_VERSION;

/**
 * Created by Gustavo on 28/02/2018.
 */

@Database(entities = ModeloLlenar.class,version =  DATABASE_VERSION)
public abstract class UserDataBase extends RoomDatabase {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "DELE-DataBase-Room";

    public abstract UserDAO userDAO();

    private static UserDataBase mInstance;

    public static UserDataBase getInstance(Context context){
        if (mInstance == null){
            mInstance = Room.databaseBuilder(context, UserDataBase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}

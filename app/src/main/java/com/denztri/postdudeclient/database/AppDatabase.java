package com.denztri.postdudeclient.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.denztri.postdudeclient.database.dao.RequestHistoryDao;
import com.denztri.postdudeclient.database.entity.RequestHistoryModel;

import java.util.Arrays;
import java.util.concurrent.Executors;

@Database(entities = {RequestHistoryModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RequestHistoryDao   requestHistoryDao();

    public static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "req_history")
                        .build();
        }
        return INSTANCE;
    }

}

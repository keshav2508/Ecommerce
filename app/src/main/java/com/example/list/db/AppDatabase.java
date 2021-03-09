package com.example.list.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Items.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShoppingListDao shoppingListDao();
    public static com.example.list.db.AppDatabase INSTANCE;

    public static com.example.list.db.AppDatabase getDBInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), com.example.list.db.AppDatabase.class,"AppDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}

package com.haybankz.dycestest.data;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.haybankz.dycestest.model.User;

/*
 * entities defines the class to be used to create tables, more tables/classes can be added by adding a comma and the object class
 * */
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase = null;

    /* @param context : Context
     * returns  appDatabase  : AppDatabase
     * this method creates and returns a singleton instance of appDatabase*/
    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            // define the appDatabase with the database name 'dycesDb'.
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "dycesDb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            ContentValues values = new ContentValues();
                            values.put("user_name", "user");
                            values.put("password", "password");
                            values.put("name", "Lorem Ipsum");
                            values.put("wallet_balance", 1000);

                            db.insert("user", OnConflictStrategy.REPLACE,values);

                        }
                    })
                    .build();
        }

        return appDatabase;
    }




    //declare an instance of userDao
    public abstract UserDao userDao();

}

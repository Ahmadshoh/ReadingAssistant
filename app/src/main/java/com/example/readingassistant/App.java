package com.example.readingassistant;

import android.app.Application;

import androidx.room.Room;

import com.example.readingassistant.data.AppDatabase;
import com.example.readingassistant.data.BookDao;


public class App extends Application {

    private AppDatabase database;
    private BookDao bookDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "reading_assistant").allowMainThreadQueries().build();

        bookDao = database.bookDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }


    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

}

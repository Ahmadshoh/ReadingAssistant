package com.example.readingassistant.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.readingassistant.models.Book;
import com.example.readingassistant.models.Category;
import com.example.readingassistant.models.relations.CategoryBookCrossRef;

@Database(entities = {Book.class, Category.class, CategoryBookCrossRef.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    public abstract CategoryDao categoryDao();
}

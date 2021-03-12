package com.example.readingassistant.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.readingassistant.models.Category;
import com.example.readingassistant.models.relations.CategoryWithBooks;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Query("SELECT * FROM categories")
    LiveData<List<Category>> getAllLiveData();

    @Query("SELECT * FROM categories WHERE categoryId = :id LIMIT 1")
    Category findById(String id);

    @Transaction
    @Query("SELECT * FROM categories")
    public List<CategoryWithBooks> getCategoryWithBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);
}

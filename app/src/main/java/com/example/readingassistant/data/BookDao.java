package com.example.readingassistant.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.readingassistant.models.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    List<Book> getAll();

    @Query("SELECT * FROM books")
    LiveData<List<Book>> getAllLiveData();

    @Query("SELECT * FROM books WHERE id = :id LIMIT 1")
    Book findById(String id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);
}

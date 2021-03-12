package com.example.readingassistant.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;


import com.example.readingassistant.models.Book;
import com.example.readingassistant.models.relations.BookWithCategories;
import com.example.readingassistant.models.relations.CategoryBookCrossRef;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    List<Book> getAll();

    @Query("SELECT * FROM books")
    LiveData<List<Book>> getAllLiveData();

    @Query("SELECT * FROM books WHERE bookId = :id LIMIT 1")
    Book findById(String id);

    @Transaction
    @Query("SELECT * FROM books")
    public List<BookWithCategories> getBookWithCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Book book);

    @Insert
    void insertCategoryBookCrossRef(CategoryBookCrossRef categoryBookCrossRef);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Delete
    void deleteCategoryBookCrossRef(CategoryBookCrossRef categoryBookCrossRef);
}

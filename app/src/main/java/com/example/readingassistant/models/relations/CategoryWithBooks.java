package com.example.readingassistant.models.relations;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.readingassistant.models.Book;
import com.example.readingassistant.models.Category;

import java.util.List;

public class CategoryWithBooks {
    @Embedded
    public Category category;
    @Relation(
            parentColumn = "categoryId",
            entityColumn = "bookId",
            associateBy = @Junction(CategoryBookCrossRef.class)
    )
    public List<Book> books;
}


package com.example.readingassistant.models.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.readingassistant.models.Book;
import com.example.readingassistant.models.Category;
import java.util.List;

public class BookWithCategories {
    @Embedded
    public Book book;
    @Relation(
            parentColumn = "bookId",
            entityColumn = "categoryId",
            associateBy = @Junction(CategoryBookCrossRef.class)
    )
    public List<Category> categories;
}
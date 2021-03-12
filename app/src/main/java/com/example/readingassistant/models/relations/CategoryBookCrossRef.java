package com.example.readingassistant.models.relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"categoryId", "bookId"})
public class CategoryBookCrossRef {
    public int categoryId;
    public int bookId;
}

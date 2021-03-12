package com.example.readingassistant.screens;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.readingassistant.App;
import com.example.readingassistant.models.Book;
import com.example.readingassistant.models.Category;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Book>> bookLiveData = App.getInstance().getBookDao().getAllLiveData();
    private LiveData<List<Category>> categoryLiveData = App.getInstance().getCategoryDao().getAllLiveData();

    public LiveData<List<Book>> getBookLiveData() {
        return bookLiveData;
    }
    public LiveData<List<Category>> getCategoryLiveData() {
        return categoryLiveData;
    }
}

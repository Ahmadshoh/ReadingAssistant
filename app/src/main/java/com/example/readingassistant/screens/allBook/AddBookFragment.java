package com.example.readingassistant.screens.allBook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.readingassistant.App;
import com.example.readingassistant.R;
import com.example.readingassistant.models.Book;

public class AddBookFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addButton = view.findViewById(R.id.addIncomeBtn);

        Book book = new Book();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    book.title = "Подсознание может всё";
                    book.author = "Джон Кехо";
                    book.description = "Book testing";
                    book.pageCount = 231;
                    book.available = true;

                    App.getInstance().getBookDao().insert(book);
            }
        });
    }
}
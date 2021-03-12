package com.example.readingassistant.screens.allBook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.readingassistant.App;
import com.example.readingassistant.R;
import com.example.readingassistant.models.Book;
import com.example.readingassistant.models.Category;
import com.example.readingassistant.models.relations.CategoryBookCrossRef;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddBookFragment extends Fragment {

//    Spinner categorySpinner;
    ChipGroup chipGroup;
    List<Category> categories;
    Category category;
    EditText title, author, description, pageCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.title);
        author = view.findViewById(R.id.author);
        description = view.findViewById(R.id.description);
        pageCount = view.findViewById(R.id.pageCount);

        chipGroup = view.findViewById(R.id.categories);

        categories = new ArrayList<Category>(App.getInstance().getCategoryDao().getAll());

        for (Category category: categories) {
            Chip chip = new Chip(requireContext());
            chip.setId(category.categoryId);
            chip.setText(category.name);
            chip.setCheckable(true);
            chip.setTextColor(getResources().getColor(R.color.white));

            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chipGroup.removeView(chip);
                }
            });

            chipGroup.addView(chip);
        }
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        MenuItem saveItem = menu.findItem(R.id.app_bar_save);
        searchItem.setVisible(false);
        saveItem.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_save: {
                Book book = new Book();

                book.title = title.getText().toString();
                book.author = author.getText().toString();
                book.description = description.getText().toString();
                book.pageCount = Integer.parseInt(pageCount.getText().toString());
                book.available = false;

                long bookId = App.getInstance().getBookDao().insert(book);

                List<Integer> ids = chipGroup.getCheckedChipIds();
                CategoryBookCrossRef categoryBookCrossRef = new CategoryBookCrossRef();
                categoryBookCrossRef.bookId = (int)bookId;

                for (Integer id : ids){
                    Chip chip = chipGroup.findViewById(id);
                    categoryBookCrossRef.categoryId = chip.getId();

                    App.getInstance().getBookDao().insertCategoryBookCrossRef(categoryBookCrossRef);
                }

                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }
}
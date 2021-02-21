package com.example.readingassistant.screens.allBook;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.readingassistant.App;
import com.example.readingassistant.R;
import com.example.readingassistant.models.Book;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<com.example.readingassistant.screens.allBook.Adapter.AllBookViewHolder>{

    private SortedList<Book> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(Book.class, new SortedList.Callback<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) (o2.id - o1.id);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Book oldItem, Book newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Book item1, Book item2) {
                return item1.id == item2.id;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public com.example.readingassistant.screens.allBook.Adapter.AllBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new com.example.readingassistant.screens.allBook.Adapter.AllBookViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.book, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.readingassistant.screens.allBook.Adapter.AllBookViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Book> book) {
        sortedList.replaceAll(book);
    }

    public Book getBookAt(int position) {
        return sortedList.get(position);
    }

    static class AllBookViewHolder extends RecyclerView.ViewHolder {

        TextView title, author;
        CheckBox reading;
//        View delete;
        Book book;


        public AllBookViewHolder(@NonNull final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);

//            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//                @Override
//                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                    return false;
//                }
////                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallBack);
////        itemTouchHelper.attachToRecyclerView(recyclerView);
//                @Override
//                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                    int position = viewHolder.getAdapterPosition();
//                    switch (direction) {
//                        case ItemTouchHelper.LEFT:
//                            App
//                            break;
//                        case ItemTouchHelper.RIGHT:
//
//                            break;
//                    }
//                }
//            });
//            daily_expense_name = itemView.findViewById(R.id.name);
//            daily_expense_amount = itemView.findViewById(R.id.amount);
//            delete = itemView.findViewById(R.id.delete);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AddDailyExpenseActivity.start((Activity) itemView.getContext(), dailyExpense, null);
//                }
//            });

//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    App.getInstance().getDailyExpenseDao().delete(dailyExpense);
//                    DailyExpenseActivity.start((Activity) itemView.getContext());
//                }
//            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(Book book) {
            this.book = book;

            title.setText(book.title);
            author.setText(book.author);
//            daily_expense_name.setText(dailyExpense.name);
//            daily_expense_amount.setText(dailyExpense.amount + " tjs");
        }
    }
}

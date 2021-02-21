package com.example.readingassistant.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "books")
public class Book implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "description")
    public String description;

//    @ColumnInfo(name = "imageLink")
//    @Nullable
//    public String imageLink;

    @ColumnInfo(name = "available")
    public boolean available;

    @ColumnInfo(name = "page_count")
    public int pageCount;

    public Book() {}

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        description = in.readString();
        available = in.readByte() != 0;
        pageCount = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(description);
        dest.writeByte((byte) (available ? 1 : 0));
        dest.writeInt(pageCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                available == book.available &&
                pageCount == book.pageCount &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, available, pageCount);
    }
}

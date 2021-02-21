package com.example.readingassistant.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "authors")
public class Author implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "biography")
    @Nullable
    public String biography;

    @ColumnInfo(name = "image_link")
    @Nullable
    public String imageLink;

    public Author() {}

    protected Author(Parcel in) {
        id = in.readInt();
        name = in.readString();
        biography = in.readString();
        imageLink = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(biography);
        dest.writeString(imageLink);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(name, author.name) &&
                Objects.equals(biography, author.biography) &&
                Objects.equals(imageLink, author.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, biography, imageLink);
    }
}

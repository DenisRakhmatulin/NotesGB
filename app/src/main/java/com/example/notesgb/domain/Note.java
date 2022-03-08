package com.example.notesgb.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class Note implements Parcelable {
    private final String id;
    private final String name;
    private final String content;
    private final Date date;

    public Note(String id, String name, String content, Date date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    protected Note(Parcel in) {
        id = in.readString();
        name = in.readString();
        content = in.readString();
        date = (Date) in.readSerializable();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(content);
        parcel.writeSerializable(date);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(name, note.name) && Objects.equals(content, note.content) && Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, date);
    }


}

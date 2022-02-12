package com.example.notesgb.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private final String id;
    private final String name;
    private final String content;
    private final String date;

    public Note(String id, String name, String content, String date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    protected Note(Parcel in) {
        id = in.readString();
        name = in.readString();
        content = in.readString();
        date = in.readString();
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

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getName() { return name; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(content);
        parcel.writeString(date);
    }
}

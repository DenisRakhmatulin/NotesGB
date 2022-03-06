package com.example.notesgb.domain;

import java.util.List;

public interface NotesRepository {

    void getNotes(Callback<List<Note>> callback);

    void add(String name, String content, Callback<Note> callback);

    void delete(Note note, Callback<Void> callback);

    Note update(String id, String newTitle, String newContent);


}

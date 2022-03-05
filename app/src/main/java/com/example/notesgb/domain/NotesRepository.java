package com.example.notesgb.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getNotes();

    Note add(String name, String content);

    void delete(Note note);

    Note update(String id, String newTitle, String newContent);

}

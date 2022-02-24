package com.example.notesgb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotesRepositoryImpl implements NotesRepository{

    private static final NotesRepository INSTANCE = new NotesRepositoryImpl();

    public static NotesRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Note> getNotes() {

        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 1", "Drink whisky", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 2", "Close the door", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 3", "Feed the cat", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 4", "Daughter's birthday", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 5", "Wife's birthday", new Date()));

        return notes;

    }
}

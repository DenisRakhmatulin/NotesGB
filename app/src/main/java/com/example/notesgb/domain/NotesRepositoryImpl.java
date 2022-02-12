package com.example.notesgb.domain;

import java.util.ArrayList;
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
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 1", "Drink whisky", "31/12/2021"));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 2", "Close the door", "10/01/2022"));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 3", "Feed the cat", "11/02/2022"));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 4", "Daughter's birthday", "17/04/2022"));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 5", "Wife's birthday", "18/10/2022"));

        return notes;

    }
}

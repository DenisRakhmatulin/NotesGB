package com.example.notesgb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class NotesRepositoryImpl implements NotesRepository {

    private static final NotesRepository INSTANCE = new NotesRepositoryImpl();

    public static NotesRepository getInstance() {
        return INSTANCE;
    }

    private final List<Note> notes = new ArrayList<>();

    public NotesRepositoryImpl() {
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 1", "Drink whisky", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 2", "Close the door", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 3", "Feed the cat", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 4", "Daughter's birthday", new Date()));
        notes.add(new Note(UUID.randomUUID().toString(), "Note # 5", "Wife's birthday", new Date()));


    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public Note add(String name, String content) {
        Note note = new Note(UUID.randomUUID().toString(), name, content, new Date());
        notes.add(note);
        return note;
    }

    @Override
    public void delete(Note note) {
        notes.remove(note);
    }

    @Override
    public Note update(String id, String newTitle, String newContent) {

        Note toChange = null;
        int indexToChange = -1;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(id)) {
                toChange = notes.get(i);
                indexToChange = i;
                break;
            }
        }

        Note newNote = new Note(toChange.getId(), newTitle, newContent, toChange.getDate());

        notes.set(indexToChange, newNote);

        return newNote;

    }
}

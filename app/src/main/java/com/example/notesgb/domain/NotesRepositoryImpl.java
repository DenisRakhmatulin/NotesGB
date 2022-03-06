package com.example.notesgb.domain;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NotesRepositoryImpl implements NotesRepository {

    private static final NotesRepository INSTANCE = new NotesRepositoryImpl();

    private Executor executor = Executors.newSingleThreadExecutor();

    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

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
    public void getNotes(Callback<List<Note>> callback) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notes);
                    }
                });

            }
        });


    }

    @Override
    public void add(String name, String content, Callback<Note> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Note note = new Note(UUID.randomUUID().toString(), name, content, new Date());
                notes.add(note);

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(note);
                    }
                });

            }
        });
    }

    @Override
    public void delete(Note note, Callback<Void> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                notes.remove(note);

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });

            }
        });
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

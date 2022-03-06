package com.example.notesgb.ui.list;

import com.example.notesgb.domain.Callback;
import com.example.notesgb.domain.Note;
import com.example.notesgb.domain.NotesRepository;

import java.util.List;

public class NotesListPresenter {

    private Note selectedNote;
    private int selectedNoteIndex;

    private final NotesListView view;
    private final NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public Note getSelectedNote() {
        return selectedNote;
    }

    public void setSelectedNote(Note selectedNote) {
        this.selectedNote = selectedNote;
    }

    public int getSelectedNoteIndex() {
        return selectedNoteIndex;
    }

    public void setSelectedNoteIndex(int selectedNoteIndex) {
        this.selectedNoteIndex = selectedNoteIndex;
    }



    public void addItem() {
        view.showProgress();

        repository.add("Added title", "Added content", new Callback<Note>() {
            @Override
            public void onSuccess(Note data) {
                view.hideProgress();

                view.addNote(data);
            }
        });

    }

    public void deleteItem() {
        view.showProgress();

        repository.delete(selectedNote, new Callback<Void>() {
            @Override
            public void onSuccess(Void data) {
                view.hideProgress();

                view.removeNote(selectedNote, selectedNoteIndex);

            }
        });

    }

    public void requestNotes() {

        view.showProgress();

        repository.getNotes(new Callback<List<Note>>() {
            @Override
            public void onSuccess(List<Note> data) {
                view.showNotes(data);

                view.hideProgress();
            }
        });
    }
}


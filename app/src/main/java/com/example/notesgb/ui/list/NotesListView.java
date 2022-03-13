package com.example.notesgb.ui.list;

import com.example.notesgb.domain.Note;

import java.util.List;

public interface NotesListView {

    void showNotes(List<Note> notes);

    void removeNote(Note note, int index);

    void showProgress();

    void hideProgress();

}

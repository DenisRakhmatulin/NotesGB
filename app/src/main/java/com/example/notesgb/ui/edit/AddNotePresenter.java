package com.example.notesgb.ui.edit;

import android.os.Bundle;

import com.example.notesgb.R;
import com.example.notesgb.domain.Callback;
import com.example.notesgb.domain.Note;
import com.example.notesgb.domain.NotesRepository;

public class AddNotePresenter extends AbstractNotePresenter{

    public static final String KEY_ADD = "AddNotePresenter";

    public AddNotePresenter(EditNoteView view, NotesRepository repository) {
        super(view, repository);
    }

    @Override
    void refresh() {
        view.setButtonTitle(R.string.btn_save);
        view.setActionButtonEnabled(false);
    }

    @Override
    void onActionButtonClicked() {

        view.showProgress();

        repository.add(title, content, new Callback<Note>() {
            @Override
            public void onSuccess(Note data) {

                Bundle bundle = new Bundle();
                bundle.putParcelable(EditNoteBottomSheetDialogFragment.ARG_NOTE, data);

                view.publishResult(KEY_ADD, bundle);

                view.hideProgress();
            }
        });
    }
}

package com.example.notesgb.ui.dialogs;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesgb.R;
import com.example.notesgb.domain.Note;
import com.example.notesgb.ui.details.NoteDetailsFragment;

public class AddNoteFragment extends Fragment {
    private EditText date;
    private EditText name;
    private EditText content;

    private static final String ARG_ADD_NOTE = "ARG_ADD_NOTE";

    public AddNoteFragment() {
        super(R.layout.fragment_add_note);
    }

    public static AddNoteFragment newInstance() {

        return new AddNoteFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        date = view.findViewById(R.id.add_date);
        name = view.findViewById(R.id.add_name);
        content = view.findViewById(R.id.add_content);

        view.findViewById(R.id.finish_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager()
                        .popBackStack();

            }
        });
    }
}

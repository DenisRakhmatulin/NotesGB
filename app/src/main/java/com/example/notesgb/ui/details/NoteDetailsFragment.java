package com.example.notesgb.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.notesgb.R;
import com.example.notesgb.domain.Note;
import com.example.notesgb.ui.list.NotesListFragment;

public class NoteDetailsFragment extends Fragment {

    public static final String TAG = "NoteDetailsFragment";
    private static final String ARG_NOTE = "ARG_NOTE";
    private EditText date;
    private TextView name;
    private EditText content;


    public static NoteDetailsFragment newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public NoteDetailsFragment() {
        super(R.layout.fragment_note_details);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = view.findViewById(R.id.date);
        name = view.findViewById(R.id.name);
        content = view.findViewById(R.id.content);

        view.findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager()
                        .popBackStack();

            }
        });


        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARG_NOTE)) {
            Note note = arguments.getParcelable(ARG_NOTE);
            updateNote(note);
        }


    }

    private void updateNote(Note note) {

        date.setText(note.getDate());
        name.setText(note.getName());
        content.setText(note.getContent());

    }
}

package com.example.notesgb.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.notesgb.R;
import com.example.notesgb.domain.Note;
import com.example.notesgb.ui.MainActivity;
import com.example.notesgb.ui.NavDrawerable;
import com.example.notesgb.ui.list.NotesListFragment;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteDetailsFragment extends Fragment {

    public static final String TAG = "NoteDetailsFragment";
    private static final String ARG_NOTE = "ARG_NOTE";
    private EditText date;
    private TextView name;
    private EditText content;
    private SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());


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

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof NavDrawerable) {
            ((NavDrawerable) requireActivity()).setAppBar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_back) {
                    Toast.makeText(requireContext(), "Back step", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager()
                            .popBackStack();
                    return true;
                }

                return false;
            }
        });

        date = view.findViewById(R.id.date);
        name = view.findViewById(R.id.name);
        content = view.findViewById(R.id.content);


        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARG_NOTE)) {
            Note note = arguments.getParcelable(ARG_NOTE);
            updateNote(note);
        }


    }

    private void updateNote(Note note) {
        
        date.setText(format.format(note.getDate()));
        name.setText(note.getName());
        content.setText(note.getContent());

    }
}

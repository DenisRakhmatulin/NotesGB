package com.example.notesgb.ui.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.notesgb.R;
import com.example.notesgb.domain.Note;
import com.example.notesgb.domain.NotesRepositoryImpl;
import com.example.notesgb.ui.MainActivity;
import com.example.notesgb.ui.NavDrawerable;
import com.example.notesgb.ui.dialogs.AddNoteFragment;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    private LinearLayout container;
    private NotesListPresenter presenter;

    public static final String ADD_PRESSED_BUNDLE = "ADD_PRESSED_BUNDLE";
    public static final String NOTE_SELECTED = "NOTE_SELECTED";

    public static final String SELECTED_NOTE_BUNDLE = "SELECTED_NOTE_BUNDLE";
    public static final String ARG_ADD = "ARG_ADD";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NotesListPresenter(this, NotesRepositoryImpl.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
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
                if (item.getItemId() == R.id.menu_add) {
                    Bundle bundle = new Bundle();
                    getParentFragmentManager()
                            .setFragmentResult(ARG_ADD, bundle);
                    return true;
                }
                if (item.getItemId() == R.id.menu_remove) {
                    Toast.makeText(requireContext(), "All notes will be removed", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


        container = view.findViewById(R.id.container);
        presenter.requestNotes();


    }

    @Override
    public void showNotes(List<Note> notes) {
        for (Note note : notes) {
            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Bundle bundle = new Bundle();
                    bundle.putParcelable(SELECTED_NOTE_BUNDLE, note);

                    getParentFragmentManager()
                            .setFragmentResult(NOTE_SELECTED, bundle);


                }
            });

            EditText date = itemView.findViewById(R.id.date);
            date.setText(note.getDate());

            TextView name = itemView.findViewById(R.id.name);
            name.setText(note.getName());

            container.addView(itemView);
        }


    }


}

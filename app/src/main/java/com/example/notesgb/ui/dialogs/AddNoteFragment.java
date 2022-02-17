package com.example.notesgb.ui.dialogs;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
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
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_back) {
                    Toast.makeText(requireContext(), "Changes lost", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager()
                            .popBackStack();
                    return true;
                }

                return false;
            }
        });

        date = view.findViewById(R.id.add_date);
        name = view.findViewById(R.id.add_name);
        content = view.findViewById(R.id.add_content);

        view.findViewById(R.id.finish_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "New note saved", Toast.LENGTH_SHORT).show();
                getParentFragmentManager()
                        .popBackStack();

            }
        });
    }
}

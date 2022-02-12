package com.example.notesgb.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;

import com.example.notesgb.R;
import com.example.notesgb.domain.Note;
import com.example.notesgb.ui.details.NoteDetailsFragment;
import com.example.notesgb.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().setFragmentResultListener(NotesListFragment.NOTE_SELECTED, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Note note = result.getParcelable(NotesListFragment.SELECTED_NOTE_BUNDLE);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, NoteDetailsFragment.newInstance(note), NoteDetailsFragment.TAG)
                        .addToBackStack("backstack1")
                        .commit();






            }
        });

    }
}
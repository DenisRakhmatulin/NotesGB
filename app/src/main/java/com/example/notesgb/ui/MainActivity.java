package com.example.notesgb.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.notesgb.R;
import com.example.notesgb.domain.Note;
import com.example.notesgb.ui.details.NoteDetailsFragment;
import com.example.notesgb.ui.dialogs.ExitBottomSheetDialogFragment;
import com.example.notesgb.ui.list.NotesListFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavDrawerable {
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            openNotes();
        }

        drawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_notes) {

                    openNotes();

                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }

                if (item.getItemId() == R.id.action_preferences) {

                    Toast.makeText(MainActivity.this, "Preferences will be here soon", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new Fragment())
                            .addToBackStack("backstack1")
                            .commit();

                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;

                }

                if (item.getItemId() == R.id.action_search) {

                    Toast.makeText(MainActivity.this, "Search will be here soon", Toast.LENGTH_SHORT).show();

                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }

                if (item.getItemId() == R.id.action_exit) {

                    ExitBottomSheetDialogFragment.newInstance().show(getSupportFragmentManager(), "ExitBottomSheetDialogFragment");
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }

                return false;
            }
        });


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

        getSupportFragmentManager().setFragmentResultListener(AuthFragment.KEY_AUTHORIZED, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                openNotes();
            }
        });


    }

    @Override
    public void setAppBar(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void openNotes() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new AuthFragment())
                    .commit();

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new NotesListFragment())
                    .commit();
        }
    }
}
package com.example.notesgb.ui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notesgb.R;
import com.example.notesgb.ui.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

public class ExitBottomSheetDialogFragment extends BottomSheetDialogFragment {
    public static ExitBottomSheetDialogFragment newInstance() {

        Bundle args = new Bundle();

        ExitBottomSheetDialogFragment fragment = new ExitBottomSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exit_bottom_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.exit_yes_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Application \"NotesGB\" closed", Toast.LENGTH_SHORT).show();
                requireActivity().finishAffinity();
                System.exit(0);
            }
        });

        view.findViewById(R.id.exit_no_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}

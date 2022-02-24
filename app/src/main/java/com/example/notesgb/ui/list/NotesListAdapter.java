package com.example.notesgb.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesgb.R;
import com.example.notesgb.domain.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> {

    public NotesListAdapter() {
    }

    interface OnNoteClicked {
        void onNoteClicked(Note note);

    }

    private final List<Note> data = new ArrayList<>();
    private final SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
    private OnNoteClicked onNoteClicked;

    public void setData(Collection<Note> toSet) {
        data.clear();
        data.addAll(toSet);
    }

    public void setOnNoteClicked(OnNoteClicked onNoteClicked) {
        this.onNoteClicked = onNoteClicked;
    }

    public OnNoteClicked getOnNoteClicked() {
        return onNoteClicked;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note item = data.get(position);

        holder.name.setText(item.getName());
        holder.date.setText(format.format(item.getDate()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        EditText date;
        TextView name;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.card).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getOnNoteClicked() != null) {
                        int clickedAt = getAdapterPosition();
                        getOnNoteClicked().onNoteClicked(data.get(clickedAt));
                    }
                }
            });

            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
        }
    }
}

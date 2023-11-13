package com.zypher.laba4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AddNoteActivity {

    public static void createNoteView(final MainActivity mainActivity, Context context, final Note note, LinearLayout notesContainer, List<Note> noteList) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.note_item, null);
        TextView titleTextView = noteView.findViewById(R.id.titleTextView);
        TextView contentTextView = noteView.findViewById(R.id.contentTextView);

        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getContent());

        noteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mainActivity.showDeleteDialog(note);
                return true;
            }
        });

        notesContainer.addView(noteView);
    }

    public static void clearInputFields(View rootView) {
        EditText titleEditText = rootView.findViewById(R.id.titleEditText);
        EditText contentEditText = rootView.findViewById(R.id.contentEditText);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }
}

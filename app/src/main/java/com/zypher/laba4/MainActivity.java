package com.zypher.laba4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "NotePrefs";
    private static final String KEY_NOTE_COUNT = "NoteCount";
    private LinearLayout notesContainer;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesContainer = findViewById(R.id.notesContainer);
        Button saveButton = findViewById(R.id.saveButton);
        noteList = new ArrayList<>();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

        loadNotesFromPreferences();
        displayNotes();
    }

    void displayNotes() {
        for (Note note : noteList) {
            AddNoteActivity.createNoteView(this, this, note, notesContainer, noteList);
        }
    }

    private void loadNotesFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT, 0);

        for (int i = 0; i < noteCount; i++) {
            String title = sharedPreferences.getString("note_title_" + i, "");
            String content = sharedPreferences.getString("note_content_" + i, "");

            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);

            noteList.add(note);
        }
    }

    private void saveNote() {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);

        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show();
        } else {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);

            noteList.add(note);
            saveNotesToPreferences();

            AddNoteActivity.createNoteView(this, this, note, notesContainer, noteList);
            AddNoteActivity.clearInputFields(findViewById(android.R.id.content));
        }
    }

    public void showDeleteDialog(final Note note) {
        DeleteNoteActivity.showDeleteDialog(this, note, noteList);
    }

    void refreshNoteNoteViews() {
        notesContainer.removeAllViews();
        displayNotes();
    }

    void saveNotesToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_NOTE_COUNT, noteList.size());
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            editor.putString("note_title_" + i, note.getTitle());
            editor.putString("note_content_" + i, note.getContent());
        }
        editor.apply();
    }
}

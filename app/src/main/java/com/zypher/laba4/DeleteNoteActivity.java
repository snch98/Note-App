package com.zypher.laba4;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import java.util.List;

public class DeleteNoteActivity {

    public static void showDeleteDialog(final MainActivity mainActivity, final Note note, final List<Note> noteList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Delete this note.");
        builder.setMessage("Are you sure want to delete this note?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteNoteAndRefresh(mainActivity, note, noteList);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private static void deleteNoteAndRefresh(MainActivity mainActivity, Note note, List<Note> noteList) {
        noteList.remove(note);
        mainActivity.saveNotesToPreferences();
        mainActivity.refreshNoteNoteViews();
    }
}

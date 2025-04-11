package com.example.tp5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ListView listViewNotes;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        listViewNotes = findViewById(R.id.listViewNotes);
        db = new DatabaseHelper(this);

        List<Note> notes = db.getAllNotes();
        ArrayAdapter<Note> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listViewNotes.setAdapter(adapter);

        listViewNotes.setOnItemClickListener((parent, view, position, id) -> {
            Note selectedNote = (Note) parent.getItemAtPosition(position);
            Intent intent = new Intent(NoteListActivity.this, NoteDetailActivity.class);
            intent.putExtra("title", selectedNote.getTitle());
            intent.putExtra("content", selectedNote.getContent());
            startActivity(intent);
        });
    }
}

package com.example.noteme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    TextView showTitle, showDescription;
    Button deleteButton;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // display the note's title and description and delete button
        showTitle = findViewById(R.id.showTitle);
        showDescription = findViewById(R.id.showDescription);
        deleteButton = findViewById(R.id.deleteButton);

        NoteDatabase db = new NoteDatabase(this);
        Intent intent = getIntent();

        // Displays the note's title and description based on the ID
        id = intent.getIntExtra("ID", 0);
        ModelNote noteModel = db.getNote(id);
        showTitle.setText(noteModel.getNoteTitle());
        showDescription.setText(noteModel.getNoteDescription());
        Toast.makeText(getApplicationContext(), "ID: "+noteModel.getId(), Toast.LENGTH_SHORT).show();

        // deletes the note from the database
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                id = intent.getIntExtra("ID", 0);
                db.deleteNote(id);
                Intent intent1 = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}


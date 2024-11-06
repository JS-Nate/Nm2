package com.example.noteme2;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;
public class NewNote extends AppCompatActivity {
    Button backButton, saveButton;
    EditText title, description;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3;
    String chosenColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        // initialize variables from the views
        backButton = findViewById(R.id.backButton);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        saveButton = findViewById(R.id.saveButton);

        // Radio buttons for selecting color
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was selected and assigns the hex color
                if (checkedId == R.id.radioButton1) {
                    chosenColor = "#FF9800";
                } else if (checkedId == R.id.radioButton2) {
                    chosenColor = "#03A9F4";
                } else if (checkedId == R.id.radioButton3) {
                    chosenColor = "#4CAF50";
                }
            }
        });





        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks if the title is empty and sends an error message if so, and doesnt store in database
                String titleCheck = title.getText().toString().trim();
                if(titleCheck.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Error: No Title", Toast.LENGTH_LONG).show();
                }

                // saves the note if title is not null
                else{
                    ModelNote noteModel = new ModelNote(title.getText().toString(), description.getText().toString(), chosenColor);
                    NoteDatabase db = new NoteDatabase(NewNote.this);
                    db.AddNote(noteModel);
                    Intent intent = new Intent(NewNote.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // back button that returns to the main page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewNote.this, MainActivity.class);  // Replace NewActivity with the desired target activity
                startActivity(intent);
                finish();
            }
        });
    }







}
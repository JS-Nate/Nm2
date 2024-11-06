package com.example.noteme2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton addButton;
    RecyclerView recyclerView;
    Adapter adapter;
    List<ModelNote> noteModelList;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To add a new note
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewNote.class);
                startActivity(intent);
            }
        });


        // search function in the EditText
        EditText searchEditText = findViewById(R.id.searchView);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });





        // Display the notes from the Database on the Main page's recycler view list
        recyclerView = findViewById(R.id.addRecyclerView);
        NoteDatabase noteDatabase = new NoteDatabase(this);
        noteModelList = noteDatabase.getNote();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, noteModelList);
        recyclerView.setAdapter(adapter);
    }


    // added for searching by filtering based on the user search
    private void filter(String query) {
        List<ModelNote> filteredList = new ArrayList<>();
        for (ModelNote note : noteModelList) {
            if (note.getNoteTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(note);
            }
        }
        adapter.filterList(filteredList);
    }

}
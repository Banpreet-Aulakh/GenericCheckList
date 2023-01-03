package com.project.genericchecklist.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.genericchecklist.R;
import com.project.genericchecklist.Utilities.DatabaseHelper;
import com.project.genericchecklist.model.ListItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckListActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private Context context;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        fab = findViewById(R.id.addItemButton);
        recyclerView = findViewById(R.id.checkList);
        db = new DatabaseHelper(this);
        addNewItemButton();


    }

    private void addNewItemButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

}
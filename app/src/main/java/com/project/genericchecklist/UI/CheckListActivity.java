package com.project.genericchecklist.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.genericchecklist.R;

public class CheckListActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ListView listView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        fab = findViewById(R.id.addItemButton);
        listView = findViewById(R.id.itemListView);

        addNewItemButton();
    }

    private void addNewItemButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked Add item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddItemActivity.class);
                startActivity(intent);
            }
        });
    }

}
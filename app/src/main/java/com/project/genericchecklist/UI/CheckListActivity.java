package com.project.genericchecklist.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.genericchecklist.R;
import com.project.genericchecklist.model.CheckList;
import com.project.genericchecklist.model.ListItem;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class CheckListActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private Context context;
    private CheckList checkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        fab = findViewById(R.id.addItemButton);
        recyclerView = findViewById(R.id.checkList);
        checkList = checkList.getInstance();

        addNewItemButton();


    }

    private void addNewItemButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListItem listItem = new ListItem();
                listItem.setTitle("Test");
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(Calendar.getInstance().getTime());
                listItem.setDate(timeStamp);
                checkList.addItem(listItem);
            }
        });
    }

}
package com.project.genericchecklist.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.genericchecklist.R;
import com.project.genericchecklist.Utilities.AdapterRecyclerView;
import com.project.genericchecklist.Utilities.DatabaseHelper;
import com.project.genericchecklist.Utilities.OnDialogCloseListener;
import com.project.genericchecklist.Utilities.RecyclerViewTouchHelper;
import com.project.genericchecklist.model.ListItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckListActivity extends AppCompatActivity implements OnDialogCloseListener {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private Context context;
    private DatabaseHelper db;
    private List<ListItem> list;
    private AdapterRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        fab = findViewById(R.id.addItemButton);
        recyclerView = findViewById(R.id.checkList);
        db = new DatabaseHelper(this);
        list = new ArrayList<>();

        initializeRecyclerView();

        list = db.getAllTasks();
        Collections.reverse(list);
        adapter.setTasks(list);

        addNewItemButton();



    }

    private void initializeRecyclerView() {
        adapter = new AdapterRecyclerView(db, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void addNewItemButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewTaskFragment.newInstance().show(getSupportFragmentManager(), NewTaskFragment.TAG);
            }
        });
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        list = db.getAllTasks();
        Collections.reverse(list);
        adapter.setTasks(list);
        adapter.notifyDataSetChanged();
    }
}
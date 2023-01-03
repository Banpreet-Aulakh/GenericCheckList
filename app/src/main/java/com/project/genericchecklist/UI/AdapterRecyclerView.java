package com.project.genericchecklist.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.genericchecklist.R;
import com.project.genericchecklist.Utilities.DatabaseHelper;
import com.project.genericchecklist.model.ListItem;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>{

    private List<ListItem> list;
    private CheckListActivity activity;
    private DatabaseHelper db;

    public AdapterRecyclerView(DatabaseHelper db, CheckListActivity activity){
        this.activity = activity;
        this.db = db;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list,
                parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ListItem listItem = list.get(position);

        holder.checkBox.setText(listItem.getTitle());
        holder.checkBox.setChecked(listItem.isDone());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(listItem.getId(), 1);
                }
                else {
                    db.updateStatus(listItem.getId(), 0);
                }
            }
        });
    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<ListItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void deleteTask(int position) {
        ListItem item = list.get(position);
        db.deleteTask(item.getId());
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ListItem item = list.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("Id", item.getId());
        bundle.putString("task", item.getTitle());

        NewTaskFragment task = new NewTaskFragment();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }
}

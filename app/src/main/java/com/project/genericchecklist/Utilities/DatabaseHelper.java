package com.project.genericchecklist.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.project.genericchecklist.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "CHECKLIST";
    private static final String TABLE_NAME = "NAME";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TASK";
    private static final String COL_3 = "CHECKED";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "TASK TEXT  , " +
                "CHECKED BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db = sqLiteDatabase;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertTask(ListItem item){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, item.getTitle());
        values.put(COL_3, item.isDone());
        db.insert(TABLE_NAME, null, values);
    }

    public void updateTask(int id, String task){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, task);
        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void updateStatus(int id, int status){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3, status);
        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id){
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID=?", new String[]{String.valueOf(id)});
    }

    public List<ListItem> getAllTasks(){
        db = this.getWritableDatabase();
        Cursor cursor = null;
        List<ListItem> listItems = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor = db.query(TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);

            if (cursor != null){
                if(cursor.moveToFirst()) {
                    do {
                        ListItem task = new ListItem();
                        task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_1)));
                        task.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COL_2)));
                        task.setDone(cursor.getInt(cursor.getColumnIndexOrThrow(COL_3)) != 0);
                        listItems.add(task);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            cursor.close();
        }
        return listItems;
    }
}

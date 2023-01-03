package com.project.genericchecklist.model;

import java.util.ArrayList;

public class CheckList {
    private ArrayList<ListItem> itemList;
    private static CheckList instance;

    private CheckList(){
        itemList = new ArrayList<>();
    }

    public static CheckList getInstance(){
        if (instance == null) {
            instance = new CheckList();
        }
        return instance;
    }

    public void addItem(ListItem item){
        itemList.add(item);
    }

    public void deleteItem(int pos){
        itemList.remove(pos);
    }

    public ListItem getItem(int pos){
        return itemList.get(pos);
    }

    public void clearList(){
        itemList.clear();
    }

    public int listSize(){
        return itemList.size();
    }

    public ArrayList<ListItem> getItemList() {
        return itemList;
    }
}

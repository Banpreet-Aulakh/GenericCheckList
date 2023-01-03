package com.project.genericchecklist.model;

import java.util.ArrayList;

public class CheckList {
    private ArrayList<Item> itemList;

    public CheckList(){
        itemList = new ArrayList<>();
    }

    public void addItem(Item item){
        itemList.add(item);
    }

    public void deleteItem(int pos){
        itemList.remove(pos);
    }

    public Item getItem(int pos){
        return itemList.get(pos);
    }

    public void clearList(){
        itemList.clear();
    }

    public int listSize(){
        return itemList.size();
    }
}

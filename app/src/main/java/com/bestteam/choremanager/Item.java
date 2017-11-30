package com.bestteam.choremanager;

/**
 * Created by frank on 2017-11-29.
 */

public class Item {
    private String name;
    private String itemType;

    public Item(){

    }

    public Item(int id, String name, String itemType){
        this.name = name;
        this.itemType = itemType;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setItemType(String itemType){
        this.itemType = itemType;
    }

    public String getName(){ return name; }
    public String getItemType(){ return itemType; }
}

package com.company;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class MapItem extends Item {
    ArrayList<ArrayList<JSONObject>> matrix;


    public MapItem(String name, ArrayList<ArrayList<JSONObject>> matrix) {
        super(name);
        this.matrix = matrix;
    }

    @Override
    public void use(Player p) {

    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Tiles:" + "\n");
        for(ArrayList<JSONObject> i : matrix) {
            for(JSONObject o : i) {
                s.append(o.get("val").toString()).append("\n");
            }
        }
        return s.toString();
    }
}

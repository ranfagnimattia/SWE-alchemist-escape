package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class GameMap {
    Integer width;
    Integer height;
    ArrayList<ArrayList<JSONObject>> matrix;

    public GameMap(String url) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./map.json"));
            JSONArray array =  (JSONArray) obj;
            matrix = new ArrayList<>(array.size());
            for (Object o : array) {
                ArrayList<JSONObject> ar = new ArrayList<>(array.size());
                JSONArray array1 = (JSONArray) o;
                for(Object ob : array1) {
                    ar.add((JSONObject) ob);
                }
                matrix.add(ar);
            }
            width = matrix.get(1).size();
            height = matrix.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MapItem generateMap(String name) {
        return new MapItem(name, this.matrix);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Width: " + width + "\n");
        s.append("Height: ").append(height).append("\n");
        s.append("Tiles:" + "\n");
        for(ArrayList<JSONObject> i : matrix) {
            for(JSONObject o : i) {
                s.append(o.get("val").toString()).append("\n");
            }
        }
        return s.toString();
    }
}

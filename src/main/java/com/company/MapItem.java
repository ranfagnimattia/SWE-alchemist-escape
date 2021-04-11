package com.company;

import org.json.JSONObject;

import java.util.ArrayList;

public class MapItem extends Item {
    ArrayList<ArrayList<JSONObject>> matrix;


    public MapItem(String name, ArrayList<ArrayList<JSONObject>> matrix) {
        super(name);
        this.matrix = matrix;
    }

    @Override
    public void use(Player p) {
        System.out.println(this.mapToString(p));
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    private String mapToString(Player p) {
        StringBuilder s = new StringBuilder();
        s.append("Map:" + "\n");
        for(ArrayList<JSONObject> i : matrix) {
            for(JSONObject o : i) {
                if(i.indexOf(o) == p.getX() && matrix.indexOf(i) == p.getY()) {
                    s.append("| ");
                    s.append(p.getName());
                    s.append(" |");
                }
                else {
                    s.append("| ");
                    String str = o.get("type").toString();
                    switch(str) {
                        case "1" -> str = "First";
                        case "2" -> str = "Enemy";
                        case "3" -> str = "Guard";
                        case "4" -> str = "Final";
                    }
                    s.append(str);
                    s.append(" |");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }
}

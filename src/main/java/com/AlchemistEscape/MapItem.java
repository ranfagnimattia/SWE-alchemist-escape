package com.AlchemistEscape;

import org.json.JSONObject;

public class MapItem extends Item {


    public MapItem(String name) {
        super(name);
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
        try {
            StringBuilder s = new StringBuilder();
            s.append("Map:" + "\n");
            for(int j = 0; j < GameMap.getInstance().getHeight() ; j++) {
                for(int i = 0; i < GameMap.getInstance().getWidth(); i++) {
                    JSONObject obj = GameMap.getInstance().getMatrix(i,j);
                    s.append("| ");
                    if(i == p.getX() && j == p.getY()) {
                        s.append(p.getName());
                    }
                    else {
                        String str = obj.get("type").toString();
                        switch(str) {
                            case "1" -> str = "First";
                            case "2" -> str = "Enemy";
                            case "3" -> str = "Guard";
                            case "4" -> str = "Final";
                        }
                        s.append(str);
                    }
                    if(!obj.has("drop") && !obj.has("enemies")) {
                        s.append(" X");
                    }
                    s.append(" |");
                }
                s.append("\n");
            }
            return s.toString();
        }
        catch (Exception e) {
            return e.toString();
        }
    }
}


/*
        for(List<JSONObject> i : matrix) {
            for(JSONObject o : i) {
                s.append("| ");
                if(i.indexOf(o) == p.getX() && matrix.indexOf(i) == p.getY()) {
                    s.append(p.getName());
                }
                else {
                    String str = o.get("type").toString();
                    switch(str) {
                        case "1" -> str = "First";
                        case "2" -> str = "Enemy";
                        case "3" -> str = "Guard";
                        case "4" -> str = "Final";
                    }
                    s.append(str);
                }
                if(!o.has("drop") && !o.has("enemies")) {
                    s.append(" X");
                }
                s.append(" |");
            }
            s.append("\n");
        }
        return s.toString(); */
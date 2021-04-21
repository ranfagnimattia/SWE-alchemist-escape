package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;


public final class GameMap {
    private static GameMap instance;

    private static Integer width;
    private static Integer height;
    private static ArrayList<ArrayList<JSONObject>> matrix;

    public static GameMap getInstance() throws IOException {
        if(instance == null)
            instance = new GameMap("map.json");
        return instance;

    }

    private GameMap(String url) throws IOException {
        String saveFileName = createSave(url,"save");

        JSONTokener parser = new JSONTokener(new File(saveFileName).toURI().toURL().openStream());
        try {
            JSONArray array = new JSONArray(parser);
            matrix = new ArrayList<>(array.length());
            for (Object o : array) {
                ArrayList<JSONObject> ar = new ArrayList<>(array.length());
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

    public void clearRoom(int x,int y, String saveFileName) throws IOException {
        matrix.get(y).get(x).remove("drop");
        matrix.get(y).get(x).remove("enemies");
        this.save(saveFileName);
    }

    public void save(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        JSONArray rootArray = new JSONArray();
        for(ArrayList<JSONObject> array : matrix) {
            JSONArray roomArray = new JSONArray();
            for(JSONObject el : array) {
                roomArray.put(el);
            }
            rootArray.put(roomArray);
        }
        fileWriter.write("");
        fileWriter.write(rootArray.toString());
        fileWriter.flush();
        fileWriter.close();
    }
    public String createSave(String url,String name) throws IOException {
        String fileName = "./" + name + ".json";
        File file = new File(fileName);
        if(!file.exists()) {
            try (
                    InputStream in = new BufferedInputStream(new FileInputStream(url));
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(name+".json"))
            )
            {
                byte[] buffer = new byte[1024];
                int lengthRead;
                while ((lengthRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, lengthRead);
                    out.flush();
                }
            }
        }
        return fileName;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public JSONObject getMatrix(int x, int y) {
        //defensive copy
        return new JSONObject(matrix.get(y).get(x).toString());
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

//cancelled
    /*
    //but there's still a problem. since jsonobject is still an object from java.org, its accessible and editable
    //so, since GameMap is a singleton, MapItem will call getMatrix method on MaptoString method
    //which will make a defensive copy of the object.
    public MapItem generateMap(String name) {
        //gives a reference of matrix so that
        //MapItem object can track whether a room is clear and show it with an X
        //but there's a problem. Since we are not making a deep copy to track rooms,
        //MapItem has a reference of an arrayList object that should be read only.
        //but MapItem could edit it if
        //return new MapItem(name, Collections.unmodifiableList(matrix));
        //best practice could be to wrap it in an unmodifiable List so that
        //if MapItem tries to do an edit operations, UnsupportedOperationException is thrown
        //we need to do this recursively

        List<List<JSONObject>> temp = new ArrayList<>();
        for (ArrayList<JSONObject> list : matrix) {
            List<JSONObject> l = new ArrayList<>();
            for(JSONObject o : list) {
                l.add(new JSONObject(o.toString()));
            }
            temp.add(Collections.unmodifiableList(l));
        }
        return new MapItem(name, Collections.unmodifiableList(temp));
    }*/
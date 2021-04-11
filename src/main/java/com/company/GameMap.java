package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;


public class GameMap {
    private Integer width;
    private Integer height;
    private ArrayList<ArrayList<JSONObject>> matrix;

    public GameMap(String url) throws IOException {
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
        this.matrix.get(y).get(x).remove("drop");
        this.matrix.get(y).get(x).remove("enemies");
        this.save(saveFileName);
    }

    public void save(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        JSONArray rootArray = new JSONArray();
        for(ArrayList<JSONObject> array : this.matrix) {
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
        System.out.println(rootArray.toString());
    }
    public static String createSave(String url,String name) throws IOException {
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

    public MapItem generateMap(String name) {
        return new MapItem(name, this.matrix);
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public ArrayList<ArrayList<JSONObject>> getMatrix() {
        return matrix;
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

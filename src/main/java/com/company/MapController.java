package com.company;

public class MapController {
    public Map map;

    public MapController(String url) {
        this.map = new Map(url);
    }
}

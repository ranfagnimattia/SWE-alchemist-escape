package com.company;

import java.util.ArrayList;

public class DropRoom implements Room {
    //Enemy enemy;
    MapItem map;
    ArrayList<Item> items;
    ArrayList<Weapon> weapons;

    public DropRoom(ArrayList<Item> items, ArrayList<Weapon> weapons) {
        this.map = null;
        this.items = items;
        this.weapons = weapons;
    }

    public DropRoom() {
    }

    public void setMap(MapItem map) {
        this.map = map;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public Boolean roomScenario(Player player) {
        return false;
    }
}

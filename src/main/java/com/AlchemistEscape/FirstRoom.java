package com.AlchemistEscape;

import java.util.ArrayList;

public class FirstRoom implements Room {
    ArrayList<Item> items;
    ArrayList<Weapon> weapons;

    public FirstRoom() {
        items = new ArrayList<>();
        weapons = new ArrayList<>();
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public Boolean roomScenario(Player player) {
        System.out.println("First Room. There are no enemies in this room.");
        player.pickUpDrops(this.items, this.weapons,null);
        player.action();
        return false;
    }
}

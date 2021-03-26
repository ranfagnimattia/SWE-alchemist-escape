package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class FirstRoom implements Room {
    ArrayList<Item> items;
    ArrayList<Weapon> weapons;

    public FirstRoom(ArrayList<Item> items, ArrayList<Weapon> weapons) {
        this.items = items;
        this.weapons = weapons;
    }

    public FirstRoom() {
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    //????
    @Override
    public Boolean roomScenario(Player player) {
        player.pickUpDrops(this.items, this.weapons,null);
        player.action();
        return false;
    }
}

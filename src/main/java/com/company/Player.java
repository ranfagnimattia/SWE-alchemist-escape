package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends CharacterStrategy {
    private Inventory inventory;
    public Player(String name) {
        super(name,5,1,5,5);
        inventory = new Inventory();
    }

    @Override
    public void attack(CharacterStrategy c) {
        System.out.println(this.name + " attacks " + c.getName() + "!");
        c.setHp(this.atk);
    }

    @Override
    public void defend() {
        System.out.println(this.name + " defends!");
    }

    public void addWeapon(Weapon w) {
        this.inventory.weapons.add(w);
    }

    public void addItem(Item i) {
        this.inventory.items.add(i);
    }

    public void addMap(MapItem m) {
        this.inventory.setMap(m);
    }


    public void mergeItems() {
        this.inventory.combineItems();
    }

    public void mergeWeapons() {
        this.inventory.combineWeapons();
    }

    public void seeMap() {
        this.inventory.getMap().use(this);
    }

    public void action() {
        do {
            System.out.println("What do you want to do?");
            ArrayList<String> actions = new ArrayList<>();
            if (this.inventory.weapons.size() > 1) {
                actions.add("merge-weapons");
                System.out.println(actions.indexOf("merge-weapons") + ") Combine Weapons.");
            }
            if (this.inventory.items.size() > 1) {
                actions.add("merge-items");
                System.out.println(actions.indexOf("merge-items") + ") Combine Items.");
            }
            if (this.inventory.getMap() != null) {
                actions.add("map");
                System.out.println(actions.indexOf("map") + ") Take a look at the map.");
            }
            actions.add("navigate");
            System.out.println(actions.indexOf("navigate") + ") Change Room.");

            Scanner in = new Scanner(System.in);
            int act;
            do {
                act = in.nextInt();
            } while (act > 0 && act < actions.size());
            switch (actions.get(act)) {
                case "merge-weapons":
                    this.mergeWeapons();
                    break;
                case "merge-items":
                    this.mergeItems();
                    break;
                case "map":
                    //take a look at the map
                    this.seeMap();
                    break;
                case "navigate":
                    //navigate
                    return;
            }
        } while(true);
    }

    public void pickUpDrops(ArrayList<Item> items, ArrayList<Weapon> weapons, MapItem map) {
        for(Item item : items) {
            System.out.println("You found " + item.getName());
            this.addItem(item);
            System.out.println(item.getName() + " added to your inventory.");
        }
        for(Weapon weapon : weapons) {
            System.out.println("You found " + weapon.getName());
            this.addWeapon(weapon);
            System.out.println(weapon.getName() + " added to your inventory.");
        }
        if(map != null) {
            System.out.println("You found " + map + "!!!");
            this.addMap(map);
            //this.addWeapon(weapon);
            //System.out.println(weapon.getName() + " added to your inventory.");
        }
    }
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mobility=" + mobility +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }
}

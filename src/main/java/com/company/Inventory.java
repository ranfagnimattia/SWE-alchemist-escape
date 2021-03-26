package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    ArrayList<Item> items;
    ArrayList<Weapon> weapons;
    MapItem map;

    public Inventory() {
        items = new ArrayList<>();
        weapons = new ArrayList<>();
        map = null;
    }

    public MapItem getMap() {
        return map;
    }

    public void setMap(MapItem map) {
        this.map = map;
    }

    void combineItems() {
        System.out.println("Select first item to merge:");
        for(int i=0; i<items.size();i++) {
            System.out.println(i + ") - "+ this.items.get(i));
        }
        Scanner in = new Scanner(System.in);
        int num1;
        do {
            num1 = in.nextInt();
            if(num1 < items.size() && num1 > 0)
                System.out.println("Select an item from the inventory.");
        } while(num1 < items.size() && num1 > 0);
        System.out.println("Select second item to merge:");
        for(int i=0; i<items.size();i++) {
            if(i != num1) {
                System.out.println(i + ") - "+ this.items.get(i));
            }
        }
        int num2;
        do {
            num2 = in.nextInt();
            if(num2 < items.size() && num2 > 0)
                System.out.println("Select an item from the inventory.");
        } while(num2 < items.size() && num2 > 0);
        ItemComposite newitem = new ItemComposite(items.get(num1).getName());
        newitem.add(items.get(num1));
        newitem.add(items.get(num2));
        items.add(newitem);
        items.remove(num1);
        items.remove(num2);
    }

    void combineWeapons() {
        System.out.println("Select first weapon to merge:");
        for(int i=0; i<weapons.size();i++) {
            System.out.println(i + ") - "+ this.weapons.get(i));
        }
        Scanner in = new Scanner(System.in);
        int num1;
        do {
            num1 = in.nextInt();
            if(num1 < weapons.size() && num1 > 0)
                System.out.println("Select a weapon from the inventory.");
        } while(num1 < weapons.size() && num1 > 0);
        System.out.println("Select second weapon to merge:");
        for(int i=0; i<weapons.size();i++) {
            if(i != num1) {
                System.out.println(i + ") - "+ this.weapons.get(i));
            }
        }
        int num2;
        do {
            num2 = in.nextInt();
            if(num2 < weapons.size() && num2 > 0)
                System.out.println("Select an item from the inventory.");
        } while(num2 < weapons.size() && num2 > 0);
        WeaponComposite newitem = new WeaponComposite(weapons.get(num1).getName());
        newitem.add(weapons.get(num1));
        newitem.add(weapons.get(num2));
        weapons.add(newitem);
        weapons.remove(num1);
        weapons.remove(num2);
    }
}

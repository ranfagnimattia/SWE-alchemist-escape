package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private final ArrayList<Item> items;
    private final ArrayList<Weapon> weapons;
    private MapItem map;

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

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    public void removeItem(int index) {
        this.items.remove(index);
    }

    public Weapon getWeapon(int index) {
        return this.weapons.get(index);
    }

    public Item getItem(int index) {
        return this.items.get(index);
    }

    void combineItems() {
        System.out.println("Select first item to merge:");
        for(int i=0; i<items.size();i++) {
            System.out.println(i + ") - "+ this.items.get(i).getName());
        }
        Scanner in = new Scanner(System.in);
        int num1;
        do {
            String str = in.next();
            num1 = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
        } while(num1 >= items.size() || num1 < 0);
        ItemComposite newitem = new ItemComposite(items.get(num1).getName());
        newitem.add(items.get(num1));
        items.remove(num1);

        System.out.println("Select second item to merge:");
        for(int i=0; i<items.size();i++) {
            System.out.println(i + ") - "+ this.items.get(i).getName());
        }
        int num2;
        do {
            String str = in.next();
            num2 = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
        } while(num2 >= items.size() || num2 < 0);
        newitem.add(items.get(num2));
        items.remove(num2);
        items.add(newitem);
    }

    void combineWeapons() {
        System.out.println("Select first weapon to merge:");
        for(int i=0; i<weapons.size();i++) {
            System.out.println(i + ") - "+ this.weapons.get(i).getName());
        }
        Scanner in = new Scanner(System.in);
        int num1;
        do {
            String str = in.next();
            num1 = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
        } while(num1 >= weapons.size() || num1 < 0);
        WeaponComposite newitem = new WeaponComposite(weapons.get(num1).getName());
        newitem.add(weapons.get(num1));
        weapons.remove(num1);


        System.out.println("Select second weapon to merge:");
        for(int i=0; i< weapons.size();i++) {
            System.out.println(i + ") - "+ this.weapons.get(i).getName());
        }
        int num2;
        do {
            String str = in.next();
            num2 = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
        } while(num2 >= weapons.size() || num2 < 0);
        newitem.add(weapons.get(num2));
        weapons.remove(num2);
        weapons.add(newitem);

    }

    @Override
    public String toString() {
        return "Inventory{" +
                "items=" + items.toString() +
                ", weapons=" + weapons.toString() +
                '}';
    }
}

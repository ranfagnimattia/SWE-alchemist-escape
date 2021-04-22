package com.AlchemistEscape;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class Player extends Observable implements CharacterStrategy {
    private Inventory inventory;
    public int equip;
    private int x;
    private int y;
    private String name;
    private Integer hp;
    private Integer atk;
    private Integer def;
    private Boolean defenseState;

    public Player(String name, int x, int y) {
        this.equip = -1;
        this.x = x;
        this.y = y;
        this.name = name;
        this.hp = 5;
        this.atk = 5;
        this.inventory = new Inventory();
        this.equip = -1;
        this.def = 5;
        this.defenseState = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Inventory getInventory() {
        return inventory;
    }



    public int getEquip() {
        return equip;
    }

    public Integer getAtk() {
        return this.atk;
    }

    public Boolean getDefenseState() {
        return defenseState;
    }

    @Override
    public Integer getDef() {
        return def;
    }

    @Override
    public void attack(CharacterStrategy c) {
        this.defenseState = true;
        super.setChanged();
        super.notifyObservers();
        int damage;
        if(equip != -1) {
            System.out.println(this.name + " attacks " + c.getName() + " with "+ this.inventory.getWeapon(equip).getName());
            damage = Math.max(this.inventory.getWeapon(equip).use() - c.getDef(), 0);
        }
        else {
            System.out.println(this.name + " attacks " + c.getName() + " with bare hands.");
            damage = Math.max(this.atk - c.getDef(), 0);
        }
        System.out.println("Damage dealt: " + damage);
        c.setHp(c.getHp() - damage);
    }

    @Override
    public void useItem() {
        this.defenseState = false;
        super.setChanged();
        super.notifyObservers();

        if(this.inventory.getItems().isEmpty()) {
            System.out.println("You have no items.");
        }
        else {
            System.out.println("Select the item you want to use:");
            for(int i=0; i<this.inventory.getItems().size();i++) {
                System.out.println(i + ") - "+ this.inventory.getItem(i).getName());
            }
            Scanner in = new Scanner(System.in);
            int it;
            do {
                String str = in.next();
                it = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
            } while(it >= this.inventory.getItems().size() || it < 0);
            this.inventory.getItem(it).use(this);
            this.inventory.removeItem(it);
        }
    }

    @Override
    public void defend() {
        this.defenseState = true;
        super.setChanged();
        super.notifyObservers();
        System.out.println(this.name + " defends!");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setEquip(int equip) {
        this.equip = equip;
    }


    public void addWeapon(Weapon w) {
        this.inventory.addWeapon(w);
    }

    public void addItem(Item i) {
        this.inventory.addItem(i);
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

    public void equip() {
        Scanner in = new Scanner(System.in);
        System.out.println("Select which weapon do you want to equip.");
        for(int i = 0; i< inventory.getWeapons().size(); i++) {
            System.out.println(i + ")" + this.inventory.getWeapon(i).getName());
        }
        int equip;
        do {
            String str = in.next();
            equip = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
        } while (equip < 0 || equip >= this.inventory.getWeapons().size());
        this.equip = equip;
    }

    public void action() {
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("What do you want to do?");
            ArrayList<String> actions = new ArrayList<>();
            if (!this.inventory.getWeapons().isEmpty()) {
                actions.add("equip");
                System.out.println(actions.indexOf("equip") + ") Equip a Weapon.");
            }
            if (this.inventory.getWeapons().size() > 1) {
                actions.add("merge-weapons");
                System.out.println(actions.indexOf("merge-weapons") + ") Combine Weapons.");
            }
            if (this.inventory.getItems().size() > 1) {
                actions.add("merge-items");
                System.out.println(actions.indexOf("merge-items") + ") Combine Items.");
            }
            if (this.inventory.getMap() != null) {
                actions.add("map");
                System.out.println(actions.indexOf("map") + ") Take a look at the map.");
            }
            actions.add("navigate");
            System.out.println(actions.indexOf("navigate") + ") Change Room.");

            int act;
            do {
                String str = in.next();
                act = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
            } while (act < 0 || act >= actions.size());
            switch (actions.get(act)) {
                case "equip":
                    this.equip();
                    break;
                case "merge-weapons":
                    this.mergeWeapons();
                    if(this.equip >= this.inventory.getWeapons().size()) {
                        System.out.println("You have to equip a new weapon.");
                        this.equip();
                    }
                    break;
                case "merge-items":
                    this.mergeItems();
                    break;
                case "map":
                    this.seeMap();
                    break;
                case "navigate":
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
            System.out.println("You found " + "\u001B[35m" + map.getName() + "!!! " + "\u001B[0m");
            this.addMap(map);
        }
    }
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }
}

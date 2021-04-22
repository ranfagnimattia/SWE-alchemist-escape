package com.AlchemistEscape;

import java.util.ArrayList;
import java.util.Scanner;

public class DropRoom implements Room {
    MapItem map;
    ArrayList<Item> items;
    ArrayList<Weapon> weapons;
    ArrayList<Enemy> enemies;


    public DropRoom() {
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.map = null;
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

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    private boolean battle(Player player) {
        Character c = new Character();
        Scanner in = new Scanner(System.in);
        for(Enemy e : enemies)
            player.addObserver(e);
        do {
            int act;
            c.setStrategy(player);
            System.out.println("1) Attack");
            System.out.println("2) Defend");
            System.out.println("3) Use item");
            do {
                String str = in.next();
                act = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
            } while(act < 1 || act > 4);
            //choose enemy
            //if enemy is dead remove from enemies
            switch (act) {
                case 1 -> {
                    for (int i = 0; i < enemies.size(); i++) {
                        System.out.println(i + ") " + enemies.get(i).getName());
                    }
                    do {
                        String str = in.next();
                        act = str.matches("-?\\d+") ? Integer.parseInt(str) : -1;
                    } while (act < 0 || act >= enemies.size());
                    c.attack(enemies.get(act));
                    if (enemies.get(act).getHp() < 1) {
                        enemies.remove(act);
                    }
                }
                case 2 -> c.defend();
                case 3 -> c.useItem();
            }
            for(Enemy e : enemies) {
                c.setStrategy(e);
                c.attack(player);
                if(player.getHp() <= 0) {
                    return false;
                }
            }
        } while(!enemies.isEmpty());
        return true;
    }

    @Override
    public Boolean roomScenario(Player player) {
        if(this.enemies.isEmpty()) {
            System.out.println("Room already cleared.");
            player.action();
        }
        else {
            System.out.println("Dungeon Guardian appears and attacks you, watch out!");
            if(this.battle(player)) {
                System.out.println("All enemies defeated. Room Clear.");
                player.pickUpDrops(this.items, this.weapons, this.map);
                player.action();
            }
            else {
                System.out.println("You died. Game Over.");
                return true;
            }
        }
        return false;
    }
}

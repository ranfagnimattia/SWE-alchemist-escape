package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class EnemyRoom implements Room{
    ArrayList<Enemy> enemies;
    ArrayList<Item> items;
    ArrayList<Weapon> weapons;


    public EnemyRoom() {
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    private void battle(Player player) {
        Character c = new Character();
        Scanner in = new Scanner(System.in);
        do {
            int act;
            c.setStrategy(player);
            System.out.println("1) Attack");
            System.out.println("2) Defend");
            System.out.println("3) Use item");
            do {
                act = in.nextInt();
            } while(act < 0 || act > 4);
            switch (act) {
                case 1:
                    //choose enemy
                    for(int i = 0; i< enemies.size(); i++) {
                        System.out.println(i + ") "+ enemies.get(i).getName());
                    }
                    do {
                        act = in.nextInt();
                    } while(act < 0 || act > enemies.size());
                    c.attack(enemies.get(act));
                    //if enemy is dead remove from enemies
                    if(enemies.get(act).getHp() < 1) {
                        enemies.remove(act);
                    }
                    break;
                case 2:
                    c.defend();
                case 3:
                    break;
            }
            for(Enemy e : enemies) {
                c.setStrategy(e);
                c.attack(player);
            }
        } while(!enemies.isEmpty());
    }
    @Override
    public Boolean roomScenario(Player player) {
        this.battle(player);
        player.pickUpDrops(this.items, this.weapons,null);
        player.action();
        return false;
    }
}

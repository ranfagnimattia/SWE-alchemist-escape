package com.AlchemistEscape;

import java.util.ArrayList;
import java.util.Scanner;

public class FinalRoom implements Room {
    ArrayList<Enemy> enemies;

    public FinalRoom() {
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
        System.out.println("In this room there's the final boss. Are you ready?");
        System.out.println("0) Yes.");
        System.out.println("1) No...");
        Scanner in = new Scanner(System.in);
        int act;
        do {
            String str = in.next();
            act = str.matches("-?\\d+") ? Integer.parseInt(str) : -1;
        } while (act != 0 && act != 1);
        if(act == 1) {
            player.action();
        }
        else {
            System.out.println("Boss is standing in front of the exit.");
            if(this.battle(player)) {
                System.out.println("You made it! You escaped!!!");
                return true;
            }
            else
                System.out.println("You died. Also, the world is destroyed. Thank you.");
                System.out.println("GAME OVER.");
        }
        return false;
    }
}

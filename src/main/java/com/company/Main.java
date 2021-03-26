package com.company;

import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        GameController game = new GameController("./map.json");
        game.play();
    }
}

        /*
        GameMap gameMap = new GameMap();
        Player player = new Player("Giorgio");
        System.out.println(gameMap.toString());
        System.out.println(player.toString());
        Item p1 = new Potion("Pozione degli orchi", 50);
        Item p2 = new Potion("Pozione delle fate", 100);
        ItemComposite megapotion = new ItemComposite("Megapozione della fenice");
        megapotion.add(p1);
        megapotion.add(p2);
        megapotion.use(player);

        player.attack(player);
        player.defend();

        System.out.println(megapotion.getClass());
        System.out.println(megapotion.getClass().toString().equals("class com.company.ItemComposite"));
        */
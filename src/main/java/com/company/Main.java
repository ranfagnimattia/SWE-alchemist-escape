package com.company;

import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Map map = new Map("./map.json");
        Player player = new Player("Giorgio");
        System.out.println(map.toString());
        System.out.println(player.toString());
        Item p1 = new Potion("Pozione degli orchi", 50);
        Item p2 = new Potion("Pozione delle fate", 100);
        ItemComposite megapotion = new ItemComposite("Megapozione della fenice");
        megapotion.add(p1);
        megapotion.add(p2);
        megapotion.use(player);

        player.attack(player);
        player.move();
        player.defend();
    }
}

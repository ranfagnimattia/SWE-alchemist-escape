package com.company;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    //deve chiamare map builder, chiamare qualcosa che fa visitare le varie stanze
    public GameMap gameMap;
    public MapBuilder mapBuilder;

    public GameController(String url) {
        this.gameMap = new GameMap(url);
        this.mapBuilder = new MapBuilder();
    }

    public void play() throws IOException, ParseException {
        Player p = new Player("Gino");

        ArrayList<String> actions = new ArrayList<>();
        int x = 0;
        int y = 0;
        Room room = this.mapBuilder.BuildRoom(x,y);
        boolean endGame = room.roomScenario(p);

        while(!endGame) {
            System.out.println("Where do you want to go?");
            if((x+1) < this.gameMap.width) {
                actions.add("right");
                System.out.println(actions.indexOf("right") + ") Go right.");
            }
            if((y+1) < this.gameMap.height) {
                actions.add("down");
                System.out.println(actions.indexOf("down") + ") Go down.");
            }
            if((x-1) < 0) {
                actions.add("left");
                System.out.println(actions.indexOf("left") + ") Go left.");
            }
            if((x+1) < 0) {
                actions.add("up");
                System.out.println(actions.indexOf("up") + ") Go up.");
            }

            Scanner in = new Scanner(System.in);
            int act;
            do {
                act = in.nextInt();
            } while (act < 0 || act > actions.size());
            switch (actions.get(act)) {
                case "right" -> x += 1;
                case "left" -> x -= 1;
                case "up" -> y -= 1;
                case "down" -> y += 1;
            }
            room = this.mapBuilder.BuildRoom(x,y);
            endGame = room.roomScenario(p);
        }
    }
}

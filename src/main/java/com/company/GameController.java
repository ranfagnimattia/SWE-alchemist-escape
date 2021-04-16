package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    //deve chiamare map builder, chiamare qualcosa che fa visitare le varie stanze
    private GameMap gameMap;
    private final MapBuilder mapBuilder;
    private GameController instance;

    public GameController(String url) throws IOException {
        this.gameMap = new GameMap(url);
        this.mapBuilder = new MapBuilder(this.gameMap);
    }

    private Player loadPlayer() throws IOException {
        String fileName = "player.json";
        File file = new File(fileName);
        if(file.exists()) {
            JSONTokener parser = new JSONTokener(file.toURI().toURL().openStream());
            //JSONParser parser = new JSONParser();
            //JSONObject obj = (JSONObject) parser.parse(new FileReader(fileName));
            JSONObject obj = new JSONObject(parser);
            Player p = new Player(obj.get("name").toString(),Integer.parseInt(obj.get("x").toString()),Integer.parseInt(obj.get("y").toString()));
            p.setHp(Integer.parseInt(obj.get("hp").toString()));
            p.setEquip(Integer.parseInt(obj.get("equip").toString()));
            p.setAtk(Integer.parseInt(obj.get("atk").toString()));
            p.setDef(Integer.parseInt(obj.get("def").toString()));

            JSONObject inventory = (JSONObject) obj.get("inventory");
            JSONArray weapon = (JSONArray) inventory.get("weapons");
            JSONArray item = (JSONArray) inventory.get("items");
            Inventory inv = new Inventory();

            ArrayList<Weapon> weapons = new ArrayList<>();
            loadWeapons(weapon,weapons);
            for(Weapon w : weapons)
                inv.addWeapon(w);

            ArrayList<Item> items = new ArrayList<>();
            loadItems(item,items);
            for(Item i : items)
                inv.addItem(i);

            System.out.println(inventory);
            if(inventory.has("map")) {
                JSONObject map = inventory.getJSONObject("map");
                inv.setMap(this.gameMap.generateMap(map.getString("name")));
            }
            p.setInventory(inv);
            return p;
        }
        else {
            return new Player("Gino",0,0);
        }
    }

    private void savePlayer(Player player) throws IOException {
        String fileName = "player.json";
        File file = new File(fileName);
        if(file.createNewFile()) {
            System.out.println("\033[0;31m" +"First Save." + "\033[0m");
        }

        FileWriter fileWriter = new FileWriter(fileName);

        JSONObject playerJSON = new JSONObject();
        playerJSON.put("name",player.getName());
        playerJSON.put("hp",player.getHp());
        playerJSON.put("atk",player.getAtk());
        playerJSON.put("def",player.getDef());
        playerJSON.put("x",player.getX());
        playerJSON.put("y",player.getY());
        playerJSON.put("equip",player.getEquip());

        JSONObject inventoryJSON = new JSONObject();
        inventoryJSON.put("weapons",createWeaponJSON(new JSONArray(),player.getInventory().getWeapons()));
        inventoryJSON.put("items",createItemJSON(new JSONArray(),player.getInventory().getItems()));
        if(player.getInventory().getMap() != null) {
            JSONObject mapJSON = new JSONObject();
            mapJSON.put("name",player.getInventory().getMap().getName());
            inventoryJSON.put("map",mapJSON);
        }

        playerJSON.put("inventory",inventoryJSON);

        //System.out.println(playerJSON.toJSONString());
        System.out.println(playerJSON.toString());
        fileWriter.write("");
        fileWriter.write(playerJSON.toString());
        fileWriter.flush();
        fileWriter.close();

    }

    private void loadWeapons(JSONArray weapon, ArrayList<Weapon> inv) {
        for(Object w : weapon) {
            JSONObject jw = (JSONObject) w;
            switch (jw.get("type").toString()) {
                case "bow" -> inv.add(new Bow(jw.get("name").toString(), Integer.parseInt(jw.get("atk").toString()), Float.parseFloat(jw.get("crit").toString()), Integer.parseInt(jw.get("combo").toString())));
                case "sword" -> inv.add(new Sword(jw.get("name").toString(), Integer.parseInt(jw.get("atk").toString()), Float.parseFloat(jw.get("crit").toString()), Integer.parseInt(jw.get("combo").toString())));
                case "weapon-composite" -> {
                    WeaponComposite weaponComposite = new WeaponComposite(jw.get("name").toString());
                    JSONArray children = (JSONArray) jw.get("children");
                    loadWeapons(children,weaponComposite.getChildren());
                    inv.add(weaponComposite);
                }
            }
        }
    }

    private void loadItems(JSONArray item, ArrayList<Item> inv) {
        for(Object i : item) {
            JSONObject ji = (JSONObject) i;
            switch (ji.get("type").toString()) {
                case "potion" -> inv.add(new Potion(ji.get("name").toString(), Integer.parseInt(ji.get("heal").toString())));
                case "item-composite" -> {
                    ItemComposite itemComposite = new ItemComposite(ji.get("name").toString());
                    JSONArray children = (JSONArray) ji.get("children");
                    loadItems(children,itemComposite.getChildren());
                    inv.add(itemComposite);
                }
            }
        }

    }

    private JSONArray createWeaponJSON(JSONArray weaponsJSON, ArrayList<Weapon> weapons) {
        for(Weapon w :weapons) {
            JSONObject obj = new JSONObject();
            switch (w.getClass().toString()) {
                case "class com.company.WeaponComposite" -> {
                    obj.put("type", "weapon-composite");
                    obj.put("name", w.getName());
                    JSONArray ar = new JSONArray();
                    createWeaponJSON(ar, ((WeaponComposite) w).getChildren());
                    obj.put("children", ar);
                }
                case "class com.company.Sword" -> {
                    obj.put("type", "sword");
                    obj.put("name", w.getName());
                    obj.put("atk", w.getAtk());
                    obj.put("combo", w.getCombo());
                    obj.put("crit", w.getCrit());
                }
                case "class com.company.Bow" -> {
                    obj.put("type", "bow");
                    obj.put("name", w.getName());
                    obj.put("atk", w.getAtk());
                    obj.put("combo", w.getCombo());
                    obj.put("crit", w.getCrit());
                }
            }
            //weaponsJSON.add(obj);
            weaponsJSON.put(obj);
        }
        return weaponsJSON;
    }

    private JSONArray createItemJSON(JSONArray itemsJSON, ArrayList<Item> items) {
        for(Item i : items) {
            JSONObject obj = new JSONObject();
            switch (i.getClass().toString()) {
                case "class com.company.ItemComposite" -> {
                    obj.put("type", "item-composite");
                    obj.put("name", i.getName());
                    JSONArray ar = new JSONArray();
                    createItemJSON(ar, ((ItemComposite) i).getChildren());
                    obj.put("children", ar);
                }
                case "class com.company.Potion" -> {
                    obj.put("type", "potion");
                    obj.put("name", i.getName());
                    obj.put("heal", ((Potion) i).getHeal());
                }
            }
            //itemsJSON.add(obj);
            itemsJSON.put(obj);
        }
        return itemsJSON;
    }

    public void play() throws IOException {
        ArrayList<String> actions = new ArrayList<>();
        Player p = this.loadPlayer();
        int x = p.getX();
        int y = p.getY();
        Room room = this.mapBuilder.BuildRoom(x,y);
        System.out.println("Welcome to Alchemist Escape!");
        System.out.println("This is a turn based RPG where you are a little alchemist.");
        System.out.println("Your goal is to escape from the dungeon you are in.");
        System.out.println("You'll escape if you find the Final room and beat the boss in that room.");
        boolean endGame = room.roomScenario(p);
        this.savePlayer(p);
        this.gameMap.clearRoom(x,y,"save.json");
        System.out.println("x: "+x+" y: "+y);
        do {
            actions.clear();
            System.out.println("x: "+x+" y: "+y);
            System.out.println("Where do you want to go?");
            if((x+1) < this.gameMap.getWidth()) {
                actions.add("right");
                System.out.println(actions.indexOf("right") + ") Go right.");
            }
            if((y+1) < this.gameMap.getHeight()) {
                actions.add("down");
                System.out.println(actions.indexOf("down") + ") Go down.");
            }
            if((x-1) >= 0) {
                actions.add("left");
                System.out.println(actions.indexOf("left") + ") Go left.");
            }
            if((y-1) >= 0) {
                actions.add("up");
                System.out.println(actions.indexOf("up") + ") Go up.");
            }

            Scanner in = new Scanner(System.in);
            int act;
            do {
                String str = in.next();
                act = str.matches("-?\\d+")? Integer.parseInt(str) : -1;
            } while (act < 0 || act > actions.size());
            switch (actions.get(act)) {
                case "right" -> x += 1;
                case "left" -> x -= 1;
                case "up" -> y -= 1;
                case "down" -> y += 1;
            }
            room = this.mapBuilder.BuildRoom(x,y);
            p.setX(x);
            p.setY(y);
            endGame = room.roomScenario(p);
            if(!endGame && !room.getClass().toString().equals("class com.company.FinalRoom")) {
                this.savePlayer(p);
                this.gameMap.clearRoom(x,y,"save.json");
            }
        } while(!endGame);
    }

}

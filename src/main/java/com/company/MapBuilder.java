package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;

public class MapBuilder {
    //deve costruire le stanze via via
    private final GameMap gameMap;
    public MapBuilder(GameMap gameMap) throws IOException {
        this.gameMap = gameMap;
    }

    public Room BuildRoom(int x, int y) throws IOException, ParseException {
        JSONObject room = this.gameMap.matrix.get(y).get(x);
        return switch (Integer.parseInt(room.get("type").toString())) {
            case 1 -> this.BuildFirstRoom(room);
            case 2 -> this.BuildEnemyRoom(room);
            case 3 -> this.BuildDropRoom(room);
            case 4 -> this.BuildBossRoom(room);
            default -> null;
        };

    }

    private DropRoom BuildDropRoom(JSONObject roominfo) {
        DropRoom room = new DropRoom();
        JSONArray drops = (JSONArray) roominfo.get("drop");
        JSONArray enemies = (JSONArray) roominfo.get("enemies");
        Boolean isBoss = Boolean.parseBoolean(roominfo.get("boss").toString());
        if(drops != null) {
            room.setItems(setItemDrops(drops));
            room.setWeapons(setWeaponDrops(drops));
            MapItem map = this.setMap(drops);
            if(map != null)
                room.setMap(map);
        }
        if(enemies != null)
            room.setEnemies(setEnemies(enemies,isBoss));
        return room;
    }

    private EnemyRoom BuildEnemyRoom(JSONObject roominfo) {
        EnemyRoom room = new EnemyRoom();
        JSONArray drops = (JSONArray) roominfo.get("drop");
        JSONArray enemies = (JSONArray) roominfo.get("enemies");
        Boolean isBoss = Boolean.parseBoolean(roominfo.get("boss").toString());
        if(drops != null) {
            room.setItems(setItemDrops(drops));
            room.setWeapons(setWeaponDrops(drops));
        }
        if(enemies != null)
            room.setEnemies(setEnemies(enemies,isBoss));
        return room;
    }

    /*
    private void BuildSecretRoom() {
    }
    */

    private FinalRoom BuildBossRoom(JSONObject roominfo) {
        FinalRoom room = new FinalRoom();
        JSONArray enemies = (JSONArray) roominfo.get("enemies");
        Boolean isBoss = Boolean.parseBoolean(roominfo.get("boss").toString());
        if(enemies != null)
            room.setEnemies(setEnemies(enemies,isBoss));
        return room;
    }

    private FirstRoom BuildFirstRoom(JSONObject roominfo) {
        FirstRoom room = new FirstRoom();
        JSONArray drops = (JSONArray) roominfo.get("drop");
        if(drops != null) {
            room.setItems(setItemDrops(drops));
            room.setWeapons(setWeaponDrops(drops));
        }
        return room;
    }


    public ArrayList<Weapon> setWeaponDrops(JSONArray drops) {
        if(drops != null) {
            ArrayList<Weapon> weapons = new ArrayList<>();
            for(Object drop : drops) {
                JSONObject obj = (JSONObject) drop;
                switch (obj.get("type").toString()) {
                    case "weapon-bow" -> weapons.add(new Bow(obj.get("name").toString(), Integer.parseInt(obj.get("atk").toString()), Float.parseFloat(obj.get("crit").toString()), Integer.parseInt(obj.get("combo").toString())));
                    case "weapon-sword" -> weapons.add(new Sword(obj.get("name").toString(), Integer.parseInt(obj.get("atk").toString()), Float.parseFloat(obj.get("crit").toString()), Integer.parseInt(obj.get("combo").toString())));
                }
            }
            return weapons;
        }
        return null;
    }

    public ArrayList<Item> setItemDrops(JSONArray drops) {
        if(drops != null) {
            ArrayList<Item> items = new ArrayList<>();
            for(Object drop : drops) {
                JSONObject obj = (JSONObject) drop;
                if (obj.get("type").toString().equals("potion")) {
                    items.add(new Potion(obj.get("name").toString(), Integer.parseInt(obj.get("heal").toString())));
                }
            }
            return items;
        }
        return null;
    }

    public ArrayList<Enemy> setEnemies(JSONArray enemies, Boolean boss) {
        if(enemies != null) {
            ArrayList<Enemy> foes = new ArrayList<>();
            if(!boss) {
                for(Object enemy : enemies) {
                    JSONObject obj = (JSONObject) enemy;
                    foes.add(new Enemy(obj.get("name").toString(),Integer.parseInt(obj.get("hp").toString()),Integer.parseInt(obj.get("atk").toString()),1));
                }
            }
            else {
                for(Object enemy : enemies) {
                    JSONObject obj = (JSONObject) enemy;
                    foes.add(new Boss(obj.get("name").toString(),Integer.parseInt(obj.get("hp").toString()),Integer.parseInt(obj.get("atk").toString()),1));
                }
            }
            return foes;
        }
        return null;
    }

    public MapItem setMap(JSONArray drops) {
        if(drops != null) {
            for(Object drop : drops) {
                JSONObject obj = (JSONObject) drop;
                if (obj.get("type").toString().equals("map")) {
                    return this.gameMap.generateMap(obj.get("name").toString());
                }
            }
        }
        return null;
    }
}

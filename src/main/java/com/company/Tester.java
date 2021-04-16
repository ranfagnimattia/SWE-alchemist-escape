package com.company;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Tester {
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    //expected to have stackOverflowError
    //ItemComposite instance calls himself and calling use() method it calls himself recursively
    //solution to what happened in this test
    // -> if clause (item added != this)
    //so you can check if the reference is the same and if true doesn't add it
    @Test(expected = StackOverflowError.class)
    public void testCompositeItems() {
        Player p = new Player("Player test",0,0);
        ItemComposite it = new ItemComposite("ItemComposite");
        Item i1 = new Potion("ItemPotion1",-1);
        Item i2 = new Potion("ItemPotion2", 50);
        i1.use(p);
        i2.use(p);

        it.use(p);

        it.add(i1);
        it.add(i2);
        it.use(p);

        it.add(it);
        it.use(p);
    }

    //last call of useItem gives argument with out of bounds indices, code doesn't consider them
    //and keep asking for a new valid input, but there is no more input and throws NoSuchElementException.
    @Test(expected = NoSuchElementException.class)
    public void testCompositeItemsAndUse() {
        Player p = new Player("TestPlayer",0,0);
        ItemComposite it = new ItemComposite("ItemComposite");
        Item i1 = new Potion("ItemPotion1",-1);
        Item i2 = new Potion("ItemPotion2", 50);
        it.add(i1);
        it.add(i2);
        p.setInventory(new Inventory());
        p.addItem(i1);
        p.addItem(i2);
        p.addItem(it);

        systemInMock.provideLines("1", "2");
        p.mergeItems();

        systemInMock.provideLines("1");
        p.useItem();

        systemInMock.provideLines("1", "2");
        p.useItem();

    }

    //last call of useItem gives argument with out of bounds indices, code doesn't consider them
    //and keep asking for a new valid input.
    @Test
    public void testObserverDefenseSystem() {
        Player player = new Player("TestPlayer",0,0); //default hp is 5
        player.setDef(0);
        //if enemy attacks, player will have 3 hp then (2 points damage)
        Enemy enemy = new Enemy("Dummy",1,2,0);
        //if enemy attacks but player defended, damage is half (so hp will go to 2)

        player.addObserver(enemy);
        enemy.attack(player);
        assertEquals("Player shouldn't be defending right now.", 3, (int) player.getHp());
        player.defend();
        enemy.attack(player);
        assertEquals("Player should be in defend mode right now.", 2, (int) player.getHp());
    }


    @Test
    public void testCharacterStrategy() {
        Player player = new Player("PlayerDummy",0,0);
        player.setHp(500);
        Enemy enemy = new Enemy("EnemyDummy",500,10,0);
        ArrayList<CharacterStrategy> characters = new ArrayList<>();
        characters.add(player);
        characters.add(enemy);

        for(int i=0; i<100;i++) {
            for(CharacterStrategy el : characters) {
                Character c = new Character();
                c.setStrategy(el);
                if(el.getClass().toString().equals("class com.company.Player"))
                    c.attack(enemy);
                else
                    c.attack(player);
            }
        }
        assertEquals("Player should be dead.", 0, (int) player.getHp());
        assertEquals("Enemy should be dead.", 0, (int) enemy.getHp());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFactoryAndRoomClear() throws IOException {
        Player player = new Player("PlayerDummy",0,0);
        Inventory inventory = new Inventory();
        inventory.addWeapon(new Bow("BowTest",1000,1f,10));
        player.setInventory(inventory);
        player.setEquip(0);

        Room room = RoomFactory.getInstance().BuildRoom(1,0);

        systemInMock.provideLines("1", "0");
        room.roomScenario(player);
    }

    @Test
    public void testFactoryAndGameOver() throws IOException {
        Player player = new Player("PlayerDummy",0,0);
        Room room = RoomFactory.getInstance().BuildRoom(1,1);

        systemInMock.provideLines("1", "0");
        room.roomScenario(player);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFactoryOutOfBounds() throws IOException {
        RoomFactory.getInstance().BuildRoom(3,5);
    }
}

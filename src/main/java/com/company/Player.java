package com.company;

public class Player extends CharacterStrategy {

    public Player(String name) {
        super(name,5,1,5,5);
    }

    @Override
    public void attack(CharacterStrategy c) {
        System.out.println(this.name + " attacks " + c.getName() + "!");
    }

    @Override
    public void defend() {
        System.out.println(this.name + " defends!");
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mobility=" + mobility +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }
}

package com.company;

public class Enemy extends CharacterStrategy {

    public Enemy(String name, Integer hp, Integer mobility, Integer atk, Integer def) {
        super(name, hp, mobility, atk, def);
    }

    @Override
    public void attack(CharacterStrategy c) {

    }

    @Override
    public void defend() {

    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mobility=" + mobility +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }
}

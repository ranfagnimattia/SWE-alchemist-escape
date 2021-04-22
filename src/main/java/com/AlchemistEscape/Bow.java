package com.AlchemistEscape;

public class Bow extends Weapon{

    public Bow(String name, Integer atk, Float crit, Integer combo) {
        super(name, atk, crit, combo);
    }

    @Override
    public Integer use() {
        Integer damage = atk;
        if(Math.random() < this.crit)
            damage *= 2;
        return damage;
    }

    @Override
    public String toString() {
        return "Bow{" +
                "name='" + name + '\'' +
                ", atk=" + atk +
                ", crit=" + crit +
                ", combo=" + combo +
                '}';
    }
}

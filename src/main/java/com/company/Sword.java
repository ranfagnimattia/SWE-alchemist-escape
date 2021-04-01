package com.company;

public class Sword extends Weapon {
    public Sword(String name, Integer atk, Float crit, Integer combo) {
        super(name, atk, crit, combo);
    }

    @Override
    public Integer use() {
        Integer damage = atk;
        if(Math.random() < this.crit)
            damage *= 3;
        return damage;
    }
}

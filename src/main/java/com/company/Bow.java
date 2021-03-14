package com.company;

public class Bow extends Weapon{

    public Bow(String name, Integer atk, Float crit, Integer combo) {
        super(name, atk, crit, combo);
    }

    @Override
    public Integer use(Character attacker, Character attacked) {
        Integer damage = (attacker.getStrategy().getAtk() + this.atk);
        damage -= attacked.getStrategy().getDef();
        if(Math.random() < this.combo)
            damage *= 2;
        Integer hp_attacked = attacked.getStrategy().getHp();
        hp_attacked = Math.max(hp_attacked - damage, 0);
        attacked.getStrategy().setHp(hp_attacked);
        return damage;
    }
}

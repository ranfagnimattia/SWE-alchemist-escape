package com.AlchemistEscape;

import java.util.Observable;
import java.util.Observer;

public class Enemy implements CharacterStrategy,Observer {
    protected String name;
    protected Integer hp;
    protected Integer atk;
    protected Integer def;
    protected Boolean playerDefState;
    public Enemy(String name, Integer hp, Integer atk, Integer def) {
        this.name= name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.playerDefState = false;
    }

    @Override
    public void attack(CharacterStrategy c) {
        System.out.println(this.name + " attacks " + c.getName() + ".");
        int damage = Math.max(this.atk - c.getDef(), 0);
        damage = this.playerDefState? damage/2 : damage;
        System.out.println("Damage dealt: " + damage);
        c.setHp(c.getHp() - damage);
    }

    @Override
    public void defend() {}

    @Override
    public void useItem() {}

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getHp() {
        return this.hp;
    }

    @Override
    public Integer getDef() {
        return this.def;
    }

    @Override
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }

    @Override
    public void update(Observable o, Object arg) {
        this.playerDefState = ((Player) o).getDefenseState();
    }
}

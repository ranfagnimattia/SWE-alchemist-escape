package com.company;

import java.util.ArrayList;

public class WeaponComposite extends Weapon {
    private ArrayList<Weapon> children;

    public WeaponComposite(String name) {
        super(name,0,0f,0);
        this.children = new ArrayList<>();
    }

    public void add(Weapon i) {
        this.children.add(i);
    }
    public void remove(Weapon i)  {
        this.children.remove(i);
    }

    public ArrayList<Weapon> getChildren() {
        return children;
    }


    @Override
    public Integer use() {
        Integer damage = 0;
        for(Weapon w : children) {
            damage += w.use();
        }
        return damage;
    }

    @Override
    public String toString() {
        return "WeaponComposite{" +
                "children=" + children.toString() +
                '}';
    }
}

package com.company;

import java.util.ArrayList;

public class WeaponComposite extends Weapon {
    private ArrayList<Weapon> children;

    public WeaponComposite(String name) {
        super(name,0,0f,0);
        this.children = new ArrayList<>();
    }

    public void add(Weapon i) {
        /*if(i.getClass().toString().equals("class com.company.WeaponComposite")) {
            WeaponComposite i_comp = (WeaponComposite) i;
            for(Weapon it : i_comp.children) {
                this.add(it);
            }
        }
        else {
            this.children.add(i);
        }*/
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

package com.company;

import java.util.ArrayList;

public class WeaponComposite extends Weapon {
    private ArrayList<Weapon> children;

    public WeaponComposite(String name) {
        super(name,0,0f,0);
        this.children = new ArrayList<>();
    }

    public void add(Weapon i) {
        if(i.getClass().toString().equals("class com.company.WeaponComposite")) {
            WeaponComposite i_comp = (WeaponComposite) i;
            for(Weapon it : i_comp.children) {
                this.add(it);
            }
        }
        else {
            this.children.add(i);
        }
        this.updateStats();
    }
    public void remove(Weapon i)  {
        this.children.remove(i);
        this.updateStats();
    }

    private void updateStats() {
        this.atk = 0;
        this.crit = 0f;
        this.combo = 0;
        for(Weapon w : children) {
            this.atk += w.getAtk();
            this.crit *= (1+ w.getCrit());
            this.combo += w.getCombo();
        }
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

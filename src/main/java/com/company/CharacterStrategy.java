package com.company;

public abstract class CharacterStrategy {
    protected String name;
    protected Integer hp;
    protected Integer mobility;
    protected Integer atk;
    protected Integer def;

    public CharacterStrategy(String name, Integer hp, Integer mobility, Integer atk, Integer def) {
        this.name = name;
        this.hp = hp;
        this.mobility = mobility;
        this.atk = atk;
        this.def = def;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMobility() {
        return mobility;
    }

    public void setMobility(Integer mobility) {
        this.mobility = mobility;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public abstract void attack(CharacterStrategy c);
    public abstract void defend();

    @Override
    public abstract String toString();
}

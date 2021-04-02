package com.company;

public abstract class Weapon {
    protected String name;
    protected Integer atk;
    protected Float crit;
    protected Integer combo;

    public Weapon(String name, Integer atk, Float crit, Integer combo) {
        this.name = name;
        this.atk = atk;
        this.crit = crit;
        this.combo = combo;
    }

    public String getName() {
        return name;
    }

    public Integer getAtk() {
        return atk;
    }

    public Float getCrit() {
        return crit;
    }

    public Integer getCombo() {
        return combo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public void setCrit(Float crit) {
        this.crit = crit;
    }

    public void setCombo(Integer combo) {
        this.combo = combo;
    }

    abstract public Integer use();

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", atk=" + atk +
                ", crit=" + crit +
                ", combo=" + combo +
                '}';
    }
}

package com.company;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    abstract public void use(Player p);

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

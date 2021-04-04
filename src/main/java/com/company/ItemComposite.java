package com.company;

import java.util.ArrayList;

//class to merge items to get stronger items
public class ItemComposite extends Item {
    private final ArrayList<Item> children;

    public ItemComposite(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public void add(Item i) {
        //if(i != this)
            this.children.add(i);
    }

    public void remove(Item i)  {
        this.children.remove(i);
    }

    public ArrayList<Item> getChildren() {
        return this.children;
    }

    @Override
    public void use(Player p) {
        for(Item i : this.children) {
            i.use(p);
        }
    }


    @Override
    public String toString() {
        return "ItemComposite{" +
                "children=" + children +
                '}';
    }
}

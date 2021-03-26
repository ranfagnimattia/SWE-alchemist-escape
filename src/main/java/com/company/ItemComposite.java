package com.company;

import java.util.ArrayList;

//class to merge items to get stronger items
public class ItemComposite extends Item {
    private final ArrayList<Item> children;

    public ItemComposite(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    //flatting of children
    public void add(Item i) {
        if(i.getClass().toString().equals("class com.company.ItemComposite")) {
            ItemComposite i_comp = (ItemComposite) i;
            for(Item it : i_comp.children) {
                this.add(it);
            }
        }
        else {
            this.children.add(i);
        }
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
}

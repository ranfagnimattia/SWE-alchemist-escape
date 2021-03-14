package com.company;


public class Potion extends Item {
    private Integer heal;

    public Potion(String name, Integer heal) {
        super(name);
        this.heal = heal;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    @Override
    public void use(Player p) {
        StringBuilder s;
        s = new StringBuilder();
        s.append("Used ").append(this.getName()).append(" on ");
        s.append(p.toString()).append("\n");
        s.append("Healed ").append(this.getHeal()).append(" hp.");
        System.out.println(s);
    }

}

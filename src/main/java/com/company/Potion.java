package com.company;


public class Potion extends Item {
    private final Integer heal;

    public Potion(String name, Integer heal) {
        super(name);
        this.heal = heal;
    }

    public Integer getHeal() {
        return this.heal;
    }

    @Override
    public void use(Player p) {
        p.setHp(p.getHp() + this.heal);
        StringBuilder s;
        s = new StringBuilder();
        s.append("Used ").append(this.getName()).append(" on ");
        s.append(p.toString()).append("\n");
        s.append("Healed ").append(this.getHeal()).append(" hp.");
        System.out.println(s);
    }


    @Override
    public String toString() {
        return "Potion{" +
                "heal=" + heal +
                '}';
    }
}

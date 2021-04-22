package com.AlchemistEscape;

public class Boss extends Enemy {
    private int trigger;

    public Boss(String name, Integer hp, Integer atk, Integer def) {
        super(name, hp, atk, def);
        this.trigger = 0;
    }

    @Override
    public void attack(CharacterStrategy c) {
        if(this.trigger++ == 3) {
            System.out.println(super.getName() + " unleashes its power! ARMAGEDDON!!!!!");
            super.attack(c);
            this.trigger = 0;
        }
        else {
            System.out.println(super.getName() + " charges its power..."+(3-this.trigger)+ " more turns...");
        }
    }

    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }
}

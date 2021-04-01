package com.company;

//context class
public class Character {
    private CharacterStrategy strategy;

    public Character() {
        this.strategy = null;
    }

    public CharacterStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CharacterStrategy strategy) {
        this.strategy = strategy;
    }

    public void attack(CharacterStrategy c) {
        if(this.strategy != null) {
            this.strategy.attack(c);
        }
    }

    public void defend() {
        if(this.strategy != null) {
            this.strategy.defend();
        }
    }

    public void useItem() {
        if(this.strategy != null) {
            this.strategy.useItem();
        }
    }
}

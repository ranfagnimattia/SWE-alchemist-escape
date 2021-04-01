package com.company;

public interface CharacterStrategy {
    public String getName();
    public Integer getHp();
    public Integer getDef();
    public void setHp(Integer hp);
    public void attack(CharacterStrategy c);
    public void defend();
    public void useItem();

    @Override
    public abstract String toString();
}

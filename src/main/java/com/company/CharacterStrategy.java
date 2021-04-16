package com.company;

public interface CharacterStrategy {
    String getName();
    Integer getHp();
    Integer getDef();
    void setHp(Integer hp);
    void attack(CharacterStrategy c);
    void defend();
    void useItem();

    @Override
    String toString();
}

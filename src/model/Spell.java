package model;

import view.View;

import java.util.ArrayList;

public class Spell extends Card {
    private static ArrayList<Spell> spells = new ArrayList<>();
    private Buff buff;

    private String range;
    public String id;

    public Spell(int hp) {
        this.setHp(hp);
        buff = new Buff(this.description);
        buff.card = this;
        this.setTypeOfAttack();
    }

    public void setTypeOfAttack() {
        this.typeOfAttack = TypeOfCard.Spell;
    }

    public void addToHp(int number) {
        this.healthLevel += number;
    }

    public void effect(Card card) {
        this.buff.buffEffect(card);
    }

    public static ArrayList<Spell> getSpells() {
        return spells;
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printSpellStats(this, i);
    }

    public String toString() {
        String info = "name: " + this.getName() + "\n" + "price: " + this.price + "\n" + "Mp: " + this.mp + "\n" + "range: " + this.range + "\n" + "description: " + this.description + "\n";
        return info;
    }

    public void attack(Card card) {
        buff.buffEffect(card);
    }
}

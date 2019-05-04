package model;

import java.util.ArrayList;

public abstract class Card {
    public String getName() {
        return name;
    }

    int attackArea;
    int numOfCardInCollection = 0;

    private String name;
    String description = " ";

    int cardIdInGame;
    ArrayList<Buff> activatedBuffs = new ArrayList<>();
    boolean sold = false;
    private Block currentBlock;
    boolean owned = false;
    boolean stunned = false;
    boolean disarmed = false;
    int price;
    int Mp;
    int mp = this.Mp;
    int Hp;
    public String cardID;

    int healthLevel;
    int Ap;
    private boolean using = false;
    TypeOfCard typeOfAttack;

    public void setName(String name) {
        this.name = name;
    }

    public void setMp(int mp) {
        Mp = mp;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setAp(int ap) {
        Ap = ap;
    }

    public void setHp(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public void call(Card card) {
        card.using = true;
    }

    public int getMp() {
        return Mp;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public int getAttackPower() {
        return Ap;
    }

    public boolean isUsing() {
        return using;
    }

    public void move() {

    }

    public static Card returnCardByName(String name) {
        Shop shop = Shop.getInstance();
        for (Card card : shop.allCards) {
            if (card.name.equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

    public static Card returnCardById(String id) {
        Shop shop = Shop.getInstance();
        for (Card card : shop.getAllCards()) {
            if (card.cardID.equals(id)) {
                return card;
            }
        }
        return null;
    }

    public TypeOfCard getTypeOfAttack() {
        return typeOfAttack;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCardID() {
        return this.cardID;
    }

    public ArrayList<Buff> getActivatedBuffs() {
        return activatedBuffs;
    }

    public void removeDeactivatedBuffs(Buff buff) {
        activatedBuffs.remove(buff);
    }

    public void addActivatedBuff(Buff buff) {
        activatedBuffs.add(buff);
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public abstract void printStats(int i);

    public abstract void attack(Card card);
}

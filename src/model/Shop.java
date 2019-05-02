package model;

import controller.Controller;
import model.menu.MainMenu;
import view.View;

import java.util.ArrayList;

public class Shop {

    private static Shop shop = new Shop();

    public static Shop getInstance() {
        return shop;
    }

    DataCenter dataCenter = DataCenter.getInstance();

    ArrayList<Card> allCards = new ArrayList<>();
    private ArrayList<Minion> allMinions = new ArrayList<>();
    private ArrayList<Spell> allSpells = new ArrayList<>();
    private ArrayList<Hero> allHeroes = new ArrayList<>();
    private ArrayList<Item> allItems = new ArrayList<>();
    private View view = View.getInstance();


    public void exitShop() {
        view.exitShop();
        Controller.currentMenu = MainMenu.getInstance();
    }

    public void showCollection() {
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public ArrayList<Minion> getAllMinions() {
        return allMinions;
    }

    public ArrayList<Spell> getAllSpells() {
        return allSpells;
    }

    public ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }

    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    public void addCard() {
        for (Hero hero : dataCenter.getHeroes()) {
            this.allCards.add(hero);
            this.allHeroes.add(hero);
        }
        for (Minion minion : dataCenter.getMinions()) {
            this.allCards.add(minion);
            this.allMinions.add(minion);
        }

        for (Spell spell : dataCenter.getSpells()) {
            this.allCards.add(spell);
            this.allSpells.add(spell);
        }
    }

    public void addItem() {
        this.allItems.addAll(dataCenter.getItems());
    }

    public int searchInCollection(String name) {
        for (Card card : this.allCards
        ) {
            if (card.getName().equals(name)) {
                return 1;
            }
        }
        return 0;
    }

    public boolean isBought(String name) {
        return true;
    }

    public void buy(String name) {
        try {

            Card card = Card.returnCardByName(name);
            Item item = findItemByName(name);
            if (card == null && item == null) {
                view.itemOrCardIsNotInShop();
                return;
            }
            if (card != null && card.price > Controller.currentAccount.getMoney()) {
                view.notEnoughMoney();
                return;
            }
            if (item != null && Integer.parseInt(item.price) > Controller.currentAccount.getMoney()) {
                view.notEnoughMoney();
                return;
            }
            if (item != null && Controller.currentAccount.getMyCollection().getMyItems().size() >= 3) {
                view.tooManyItemsInMyCollection();
                return;
            }
            if (card != null) {
                Controller.currentAccount.reduceMoney(card.getPrice());
                Controller.currentAccount.getMyCollection().adCardToCollection(card);
                view.cardBought();
                view.remainingMoney();
                return;
            }
            if (item != null) {
                if (item.getPrice().matches("[0-9]+")) {
                    Controller.currentAccount.reduceMoney(Integer.parseInt(item.getPrice()));
                }
                Controller.currentAccount.getMyCollection().addItemToCollection(item);
                view.itemBought();
                view.remainingMoney();
            }
        } catch (NumberFormatException e) {
            e.getMessage();
            throw new NumberFormatException("you can not buy a collectible!!!");
        }
    }

    public void show() {
        view.showMinions();
        view.showSpells();
        view.showHeroesInShop();
        view.showItems();
    }

    public Card search(String name) {
        for (Card card : shop.allCards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public void help() {
        view.printShopHelpMenu();
    }

    public void sell(String name) {
        Card card = Controller.currentAccount.getMyCollection().findCardInCollection(name);
        Item item = Controller.currentAccount.getMyCollection().findItemInCollection(name);
        if (card.equals(null) && item.equals(null)) {
            view.notOwnThisCardOrItem();
            return;
        }
        if (!card.equals(null)) {
            Controller.currentAccount.sellCard(card);
            Controller.currentAccount.getMyCollection().removeCardFromCollection(card);
            Controller.currentAccount.addMoney(card.getPrice());
            view.cardSold();
            return;
        }
        if (!item.equals(null)) {
            Controller.currentAccount.getMyCollection().removeItemFromCollection(item);
            Controller.currentAccount.addMoney(Integer.parseInt(item.getPrice()));
            view.itemSold();
        }
    }

    public boolean isSold(String name) {
        Card card = Card.returnCardByName(name);
        return card != null && !Controller.currentAccount.getMyCollection().hasCard(card) && Controller.currentAccount.soldCard(card);
    }

    public void showMenu() {

    }

    private Item findItemByName(String name) {
        for (Item item : DataCenter.getInstance().getItems()) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


}

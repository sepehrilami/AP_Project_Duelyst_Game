package model;

import java.util.ArrayList;
import java.util.Random;

public class Hand {
    ArrayList<Card> allCardsInDeck = new ArrayList<>();
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private ArrayList<Card> cardsUsedInGame = new ArrayList<>();
    private Card cardToBeAdded;

    public void initializeHand() {
        //should be called at the beginning of game
        Hero hero = new Hero();
        for (Card card : allCardsInDeck) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                hero = (Hero) card;
                break;
            }
        }
        allCardsInDeck.remove(hero);
        Random random = new Random();
        cardsInHand.add(allCardsInDeck.get(random.nextInt(allCardsInDeck.size())));
        int j = 4;
        for (int i = 0; i < j; i++) {
            Card card = allCardsInDeck.get(random.nextInt(allCardsInDeck.size()));
            if (!cardsInHand.contains(card)) {
                cardsInHand.add(card);
            } else {
                j++;
            }
        }
    }

    public Card getCardToBeAdded() {
        if (cardToBeAdded == null) {
            getNextCard();
        }
        return cardToBeAdded;
    }

    private void getNextCard() {
        Random random = new Random();
        int j = 1;
        for (int i = 0; i < j; i++) {
            Card card = allCardsInDeck.get(random.nextInt(allCardsInDeck.size()));
            if (!cardsInHand.contains(card) && !cardsUsedInGame.contains(card)) {
                cardToBeAdded = card;
            } else if ((allCardsInDeck.size() - cardsUsedInGame.size() - cardsInHand.size()) == 0) {
                cardToBeAdded = null;
                break;
            } else {
                j++;
            }
        }
    }

    public ArrayList<Card> returnHand() {
        if (cardsInHand.size() < 5) {
            addToHand();
        }
        return cardsInHand;
    }

    private void addToHand() {
        while (cardsInHand.size() < 5) {
            cardsInHand.add(cardToBeAdded);
            if ((allCardsInDeck.size() - cardsUsedInGame.size() - cardsInHand.size()) == 0) {
                break;
            }
            getNextCard();
        }
    }

    public void deleteFromHand(Card card) {
        cardsInHand.remove(card);
        cardsUsedInGame.add(card);
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
}

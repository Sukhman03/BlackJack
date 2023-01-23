import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class DeckOfCards {
    private static final int CARD_COUNT = 52;
    private Card[] deck = new Card[CARD_COUNT];
    private Stack<Card> shuffledDeck = new Stack<Card>();

    public DeckOfCards() { //initializes array of cards, puts all cards into array
        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight",
        "Nine", "Ten", "Jack", "Queen", "King",};
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};

        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public void shuffle() { // puts cards into stack in random order
        Random rand = new Random();
        while (shuffledDeck.size() < CARD_COUNT) {
            int randomNum = rand.nextInt(52);
            if (!shuffledDeck.contains(deck[randomNum])) { // if the card isn't in the array, it will add it
                shuffledDeck.add(deck[randomNum]);
            }
        }
    }

    public Card dealCard() { // returns top card in the deck
        if (shuffledDeck.size() > 0) {
            return shuffledDeck.pop();
        } else {
            return null; // returns null if there are no cards left in stack
        }
    }

    public int getCardValueOf(String card) { // returns the point value of the card
        int i = card.indexOf(' ');
        Scanner input = new Scanner(System.in);
        card = card.substring(0, i);
        switch(card) {
            case "Ace":
                System.out.println("You drew an Ace! Would you like the Ace to be one or eleven? Type '1' or '11': ");
                int aceValue = input.nextInt();
                return aceValue;
            case "Deuce":
                return 2;
            case "Three":
                return 3;
            case "Four":
                return 4;
            case "Five":
                return 5;
            case "Six":
                return 6;
            case "Seven":
                return 7;
            case "Eight":
                return 8;
            case "Nine":
                return 9;
            case "Ten":
            case "Jack":
            case "Queen":
            case "King":
                return 10;
            default:
                return 0;

        }
    }

}

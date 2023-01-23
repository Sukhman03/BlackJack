import java.util.Arrays;
import java.util.Scanner;

public class PlayGame {
    private String[] playersHand = new String[12];
    private String[] dealersHand = new String[12];
    private int playersHandCount = 0; // tracks amount of cards in players hand
    private int dealersHandCount = 0; // tracks amount of cards in dealers hand
    private int playerCardValues = 0; // tracks player points
    private int dealersCardValue = 0; // tracks dealers points
    private DeckOfCards deck = new DeckOfCards();
    public PlayGame() {
        deck.shuffle();
    }

    public void gameManager() { // manages the game loop
        char hitOrStand = 'O';
        rules();
        firstTwoCards();
        if (playerCardValues < 22 ) { // game will stop the game if the player draws 2 (11 point) Aces on first turn
            hitOrStand = hitOrStand();
        }
        
        while (hitOrStand == 'H') {
            if (playerCardValues > 21) { // if player loses, stop
                break;
            }
            if (dealersCardValue > 21) { // if dealer loses, stop
                break;
            }
                hit();
                dealerPlay(false);
                printCards();
                if (playerCardValues < 22) { // stops the print if player loses on this turn
                    hitOrStand = hitOrStand();
                }
        }
        
        while (dealersCardValue < 17) { // ensures dealer hits 17 after player stands
            dealerPlay(false);
        }
        
        if (playerCardValues < 22 && dealersCardValue < playerCardValues) { // ensures that if dealer stops at 17
            while (dealersCardValue < 22) {                                 // player cannot stop and win at 18-20
                dealerPlay(true);                                               // with the dealer giving up
            }
        }
        results();
    }

    public char hitOrStand () { // asks player if they wish to hit or stand
        Scanner scan = new Scanner(System.in);
        System.out.println("\nWould you like to Hit or Stand?");
        return scan.next().toUpperCase().charAt(0);
    }
    
    public void hit() { // what happens if player chooses hit
        String drawCard = String.valueOf(deck.dealCard());
        playersHand[playersHandCount] = drawCard;
        int card = deck.getCardValueOf(playersHand[playersHandCount]); // sends the card to get the points evaluated
        playerCardValues += card;                                      // in DeckOfCards class
        playersHandCount++;
    }

    public int getDealerCardValue() { // gets dealerCardValue
        return dealersCardValue;
    }
    
    public void results() { // prints out the results based on the ending
        System.out.println("\nFinal Cards: \n"); // prints out the final hands after game over
        printCards();
        System.out.println();
        if (playerCardValues < dealersCardValue && dealersCardValue < 22) {
            System.out.println("You have lost to the dealer.");
        } else if (playerCardValues > 22 && dealersCardValue < 22) {
            System.out.println("You have gone over 21, you lose.");
        } else if (playerCardValues > 22 & dealersCardValue > 22) {
            System.out.println("You and the dealer have gone over 21, it is a tie.");
        } else if (playerCardValues == dealersCardValue) {
            System.out.println("You and the dealer have the same amount of points, it is a tie.");
        } else if (playerCardValues < 22 && dealersCardValue > 21) {
            System.out.println("The dealer has gone over 21, you have won!");
        } else if (playerCardValues < 22 && playerCardValues > dealersCardValue) {
            System.out.println("Congratulations, you have won!");
        }
    }

    public void dealerPlay(boolean ifLosing) { // manages
        if (dealersCardValue < 17 || ifLosing == true) {
            int card;
            System.out.println("Dealer chose to hit! ");
            System.out.println();
            String drawCard = String.valueOf(deck.dealCard());
            dealersHand[dealersHandCount] = drawCard;
            if (dealersHand[dealersHandCount].contains("Ace")) {
                if (dealersCardValue < 11) {
                    card = 11;
                } else {
                    card = 1;
                }
            } else {
                card = deck.getCardValueOf(dealersHand[dealersHandCount]);
            }
            dealersCardValue += card;
            dealersHandCount++;
        } else {
            System.out.println("Dealer chose to stand!");
            System.out.println();
        }
    }
    
    public void printCards() {
        System.out.println("Your Hand: ");
        for (int i = 0; i < playersHandCount; i++) {
            System.out.println(playersHand[i]);
        }
        System.out.println("Card Value: " + playerCardValues);
        System.out.println("\nDealers Hand:");
        for (int i = 0; i < dealersHandCount; i++) {
            System.out.println(dealersHand[i]);
        }
        System.out.println("Card Value: " + dealersCardValue);
    }
    
    public void firstTwoCards() { // gives the dealer and player the initial two cards
        for (int i = 0; i < 2; i++) {
            String drawCard = String.valueOf(deck.dealCard());
            playersHand[playersHandCount] = drawCard;
            int card = deck.getCardValueOf(playersHand[playersHandCount]);
            playerCardValues += card;
            playersHandCount++;
            drawCard = String.valueOf(deck.dealCard());
            dealersHand[dealersHandCount] = drawCard;
            if (dealersHand[dealersHandCount].contains("Ace")) {
                if (dealersCardValue < 11) {
                    card = 11;
                } else {
                    card = 1;
                }
            } else {
                card = deck.getCardValueOf(dealersHand[dealersHandCount]);
            }
            dealersCardValue += card;
            dealersHandCount++;
        }
        printCards();
    }

    public void rules() { // if the user doesn't know the rules
        System.out.println("Welcome to Blackjack! \nThe rules are: ");
        System.out.println("You will be dealt two cards at the start of the game.");
        System.out.println("The objective of the game is to get as close to 21 without going over whilst beating ");
        System.out.println("the dealer. The dealer will also be dealt two cards and has same objective");
        System.out.println("If you wish to draw another card, type 'Hit'.");
        System.out.println("If you wish to stop drawing cards, type 'Stand'.");
        System.out.println("Aces are worth 1 or 11 (Your choice), Face cards are worth 10, and the other ");
        System.out.println("cards are worth their face values (ie. 9 of Spades is worth 9).");
        System.out.println();
        System.out.println();
    }
}


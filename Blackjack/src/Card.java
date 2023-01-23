public class Card {
    private final String suit;
    private final String face;

    public Card(String face, String suit) {
        this.suit = suit;
        this.face = face;
    }

    public String toString() { //builds the cards
        return face + " of " + suit;
    }
}

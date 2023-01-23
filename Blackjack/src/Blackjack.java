// Programmer: Sukhman Lally
// Class: CS 145: Face to Face
// Date: 01/21/2023
// Assignment: Lab 4: Card Game
// Reference Materials:
// Purpose: I made a blackjack game using a stack and multiple classes (stack is created in DeckOfCards.java [line 7])

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        PlayGame game = new PlayGame();
            game.gameManager();
    }
}
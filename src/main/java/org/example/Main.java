package org.example;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Game game;
        int maxScore = 0;
        System.out.println("Welcome to Reversi game");
        String command = "ok";
        while (!command.equals("exit")) {
            System.out.print("================================\n");
            System.out.println("Enter one of this commands:\nexit - to exit program\nmax - maximum score\nsolo - game with computer\nduo - game with friend");
            command = scanner.nextLine();
            switch (command) {
                case "max" -> System.out.println("Current max score is: " + maxScore);
                case "solo" -> {
                    game = new Game(1);
                    if (game.getScore() > maxScore) {
                        maxScore = game.getScore();
                    }
                }
                case "duo" -> new Game(2);
                default -> {
                }
            }
        }
        System.out.println("Brawl Stars is the best. Bye.");
    }
}
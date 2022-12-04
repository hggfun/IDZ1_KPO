package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Game {
    Playground playground;
    Scanner scanner = new Scanner(System.in);
    Random rnd = new Random();
    private int score;

    public Game(int t) {
        playground = new Playground();
        if (t == 1) {
            solo();
        } else {
            duo();
        }
    }

    public int getScore() {
        return score;
    }

    private void solo() {
        int x, y;
        print(1);
        for (int i = 0; i < 30; i++) {
            System.out.println("Your turn. Enter coordinates.");
                x = readValue();
                y = readValue();
            while (!playground.check(1, x, y)) {
                System.out.println("Incorrect value.");
                x = readValue();
                y = readValue();
            }
            playground.change(1, x, y);
            System.out.print("\n=================================================\n");
            print(2);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ignored) {

            }
            playground.change(1, x, y);
            x = rnd.nextInt(8);
            y = rnd.nextInt(8);
            while (!playground.check(2, x, y)) {
                x = rnd.nextInt(8);
                y = rnd.nextInt(8);
            }
            playground.change(2, x, y);
            System.out.print("\n=================================================\n");
            print(1);
        }
        int playerScore = 0;
        int compScore = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (playground.getByIndex(i, j, 1) == 1) {
                    playerScore++;
                } else if (playground.getByIndex(i, j, 2) == 2) {
                    compScore++;
                }
            }
        }
        if (playerScore > compScore) {
            System.out.println("Congratulations! You won! Your score is " + playerScore);
        } else {
            System.out.println("You didn't win, Your score is " + playerScore + ", while computer's score is " + compScore);
        }
        score = playerScore;
    }

    private void duo() {
        int x, y;
        for (int i = 0; i < 30; i++) {
            int t = print(1);
            if (t == 0) {
                break;
            } else if (t > 1) {
                System.out.println("Player 1 turn. Enter coordinates.");
                x = readValue();
                y = readValue();
                while (!playground.check(1, x, y)) {
                    System.out.println("Incorrect valuesolo.");
                    x = readValue();
                    y = readValue();
                }
                playground.change(1, x, y);
            }
            t = print(2);
            if (t == 0) {
                break;
            } else if (t > 1) {
                System.out.println("Player 2 turn. Enter coordinates.");
                x = readValue();
                y = readValue();
                while (!playground.check(2, x, y)) {
                    System.out.println("Incorrect value.");
                    x = readValue();
                    y = readValue();
                }
                playground.change(2, x, y);
            }
        }
        int firstScore = 0;
        int secondScore = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (playground.getByIndex(i, j, 1) == 1) {
                    firstScore++;
                } else if (playground.getByIndex(i, j, 2) == 2) {
                    secondScore++;
                }
            }
        }
        if (firstScore == secondScore) {
            System.out.println("Draft! You both got " + firstScore + " points");
        } else if (firstScore > secondScore) {
            System.out.println("First player won with score: " + firstScore);
        } else {
            System.out.println("Second player won with score: " + secondScore);
        }
    }
    private int readValue() {
        int x;
        while(true) {
            try {
                x = scanner.nextInt();
                if (x < 8 && x > -1) {
                    return x;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
            System.out.println("Incorrect input format!");
        }
    }
    private int print(int player) {
        System.out.print("\n=================================================\n");
        System.out.println("|-----+-----+-----+-----+-----+-----+-----+-----|");
        int zeros = 0, ones = 0, twos = 0;
        for (int i = 0; i < 8; i++) {
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                switch (playground.getByIndex(i, j, player)) {
                    case 0 -> {
                        System.out.print(" " + i + " " + j + " ");
                        zeros++;
                    }
                    case 1 -> {
                        System.out.print("  *  ");
                        ones++;
                    }
                    case 2 -> {
                        System.out.print("  #  ");
                        twos++;
                    }
                    case 3 -> System.out.print("     ");
                }
                System.out.print("|");
            }
            System.out.println();
            System.out.println("|-----+-----+-----+-----+-----+-----+-----+-----|");
        }
        if (ones == 0 || twos == 0) {
            return 0;
        } else if (zeros == 0) {
            return 1;
        }
        return 2;
    }
}

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
        int [] coordinates;
        int x, y;
        for (int i = 0; i < 30; i++) {
            int t = print(1);
            if (t == 0) {
                break;
            } else if (t > 1) {
                System.out.println("Your turn. Enter coordinates.");
                coordinates = readValues();
                x = coordinates[0];
                y = coordinates[1];
                while (!playground.check(1, x, y)) {
                    System.out.println("Incorrect value.");
                    coordinates = readValues();
                    x = coordinates[0];
                    y = coordinates[1];
                }
                playground.change(1, x, y);
            }
            System.out.print("\n=================================================\n");
            t = print(2);
            if (t == 0) {
                break;
            } else if (t > 1) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ignored) {

                }
                x = rnd.nextInt(8);
                y = rnd.nextInt(8);
                while (!playground.check(2, x, y)) {
                    x = rnd.nextInt(8);
                    y = rnd.nextInt(8);
                }
                playground.change(2, x, y);
            }
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
        int[] coordinates;
        for (int i = 0; i < 30; i++) {
            int t = print(1);
            if (t == 0) {
                break;
            } else if (t > 1) {
                System.out.println("Player 1 turn. Enter coordinates.");
                coordinates = readValues();
                x = coordinates[0];
                y = coordinates[1];
                while (!playground.check(1, x, y)) {
                    System.out.println("Incorrect valuesolo.");
                    coordinates = readValues();
                    x = coordinates[0];
                    y = coordinates[1];
                }
                playground.change(1, x, y);
            }
            t = print(2);
            if (t == 0) {
                break;
            } else if (t > 1) {
                System.out.println("Player 2 turn. Enter coordinates.");
                coordinates = readValues();
                x = coordinates[0];
                y = coordinates[1];
                while (!playground.check(2, x, y)) {
                    System.out.println("Incorrect value.");
                    coordinates = readValues();
                    x = coordinates[0];
                    y = coordinates[1];
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
    private int[] readValues() {
        int[] coordinates = new int[2];
        String str;
        String[] values;
        while(true) {
            str = scanner.nextLine();
            values = str.split(" ");
            while (values.length != 2) {
                System.out.println("Enter 2 values!");
                str = scanner.nextLine();
                values = str.split(" ");
            }
            try {
                coordinates[0] = Integer.parseInt(values[0]);
                coordinates[1] = Integer.parseInt(values[1]);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input!");
            }
        }
        return coordinates;
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
                        System.out.print("  ●  ");
                        ones++;
                    }
                    case 2 -> {
                        System.out.print("  ©  ");
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

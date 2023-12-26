import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.io.IOException;

public class JangMan {

    private final int MAX_WRONG_GUESSES = 7;
    private String used_characters = "";
    private int wrong_guesses = 0;
    private String[] word_list = {"donau", "typ", "lokomotive", "atmosphäre", "rythmus", "gymnastik"};
    private String current_word;

    public JangMan() {
        Random r = new Random();
        this.current_word = word_list[r.nextInt(word_list.length)];
    }

    public JangMan(String word) {
        if(word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        this.current_word = word.toLowerCase();
    }

    public boolean makeGuess(char guess) {
        String Sguess = String.valueOf(guess).trim().toLowerCase();
        if(current_word.contains(Sguess) && !used_characters.contains(Sguess)) {
            used_characters += Sguess;
            System.out.println("| correct!");
            return true;
        }else {
            System.out.println("| wrong!");
            wrong_guesses++;
            return false;
        }
    }

    public String getCurrentWord() {
        return this.current_word;
    }

    public int wrongTriesLeft() {
        return MAX_WRONG_GUESSES - wrong_guesses;
    }

    public String solution() {
        return current_word;
    }

    public String status() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAX_WRONG_GUESSES; i++) 
            if(i < wrong_guesses) sb.append('X');
            else sb.append('0');

        return sb.toString();
    }

    public String toString() {
        return IntStream.range(0, current_word.length())
                    .mapToObj(i -> {
                        char c = current_word.toCharArray()[i];
                        if (!used_characters.contains(String.valueOf(c))) {
                            return '_';
                        } else {
                            return c;
                        }
                    })
                    .map(Object::toString)
                    .collect(Collectors.joining());
    }

    public boolean isWon() {
        return current_word.equals(toString());
    }

    public boolean isGameOver() {
        return wrongTriesLeft() == 0;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to my little hangman game! You know the rules. Let's begin!");

        while (!isGameOver() && !isWon()) {
            clearConsole();
            System.out.println("┌───────────────────────────────────────────┐");
            System.out.println("│               Hangman Game                │");
            System.out.println("├───────────────────────────────────────────┤");
            System.out.println("│ Tries left: " + status());
            System.out.println("│ Word: " + toString());
            System.out.print("│ Please input a guess character (only 1): ");
            
            String guess = sc.nextLine();
            if(guess.length() > 1) {
                clearConsole();
                System.out.println("| Please input only one character! |");
                pause(2000);
                continue;
            }

            try {
                makeGuess(guess.charAt(0));
            } catch (Exception e) {
                clearConsole();
                System.out.println("| Please input a character! |");
                pause(2000);
                continue;
            }
            System.out.println("│ Used characters: " + used_characters);
            System.out.println("└───────────────────────────────────────────┘");
            pause(2000);
            clearConsole();
        }

        if (isWon()) {
            System.out.println("Congratulations, you won!");
        } else {
            System.out.println("Welp, maybe next time.");
        }

        sc.close();
    }

    private void clearConsole() {
        //definetly not copied :)
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error while clearing console.", e);
        }
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to pause");
        }
    }
}

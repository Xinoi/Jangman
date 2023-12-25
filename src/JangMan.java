import java.util.Random;
import java.util.Scanner;

public class JangMan {

    private final int MAX_WRONG_GUESSES = 10;
    private String used_characters;
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
        if(!current_word.contains(Sguess) || used_characters.contains(Sguess)) {
            used_characters += Sguess;
            return true;
        }else {
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

    public String toString() {
        //to do
        return null;
    }

    public boolean isWon() {
        //to do
        return false;
    }

    public boolean isGameOver() {
        if (wrongTriesLeft() == 0) return true;
        else return false;
    }
}

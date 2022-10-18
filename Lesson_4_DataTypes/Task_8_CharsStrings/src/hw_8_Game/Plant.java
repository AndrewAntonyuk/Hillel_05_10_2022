package hw_8_Game;

import java.util.Random;
import java.util.Scanner;

public class Plant {
    private final int size = 15;
    private final String setWordForGuess;
    private final char[] hidedChars;
    private final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot"
            , "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea"
            , "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    private String actualWordForGuess;

    //region Constructors
    public Plant() {
        setWordForGuess = initWordForGuess();
        hidedChars = new char[size];
        firstHidingChars();
    }
    //endregion

    private String initWordForGuess() {
        Random random = new Random();
        int pos = random.nextInt(words.length);

        return words[pos];
    }

    public void actionsForGame() {
        boolean isGuessed = false;
        String hidedWord;

        do {
            actualWordForGuess = askWordForGuess();

            if (actualWordForGuess.equals(setWordForGuess)) {
                isGuessed = true;
                System.out.println("Congratulates!!! \nYou are winner!");
            } else {
                hidedWord = hidingCharsInWord();
                System.out.println("Try again! Set word similar with: " + hidedWord);
            }
        } while (!isGuessed);
    }

    private String askWordForGuess() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your word for guess:");

        return scanner.next();
    }

    private String hidingCharsInWord() {
        int minLen = findMinLen();

        for (int i = 0; i < minLen; i++) {
            if (setWordForGuess.charAt(i) == actualWordForGuess.charAt(i)) {
                hidedChars[i] = setWordForGuess.charAt(i);
            }
        }

        return new String(hidedChars);
    }

    private int findMinLen() {
        int ret;

        if (setWordForGuess.length() < actualWordForGuess.length()) {
            ret = setWordForGuess.length();
        } else {
            ret = actualWordForGuess.length();
        }

        return ret;
    }

    private void firstHidingChars() {
        for (int i = 0; i < size; i++) {
            hidedChars[i] = '#';
        }
    }
}

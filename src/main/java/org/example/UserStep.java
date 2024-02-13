package org.example;

import java.util.HashSet;
import java.util.List;

import static org.example.ComputerStep.*;
import static org.example.GameWindow.*;
import static org.example.GreatingWindow.cities;

public class UserStep {
    static int userScore = 0;
    static char lastUserWordChar;

    public static void userStep(String input, String lastComputerWord) throws InterruptedException {

        // Has the user entered "здаюсь"?
        if (isEndFromUser(input)) return;

        // We check if the city entered by the user is in the list of cities
        if (!isCityInList(input, cities, lastComputerWordChar)) return;

        // Check if we are entering the word from the required letter
        if (lastComputerWord != null) {
            if (!isCorrectFirstChar(input, lastComputerWordChar)) return;
        }

        // Checking for repetition from the user
        if (isRepeatedByUser(input, usedCities, lastComputerWordChar)) return;

        usedCities.add(input);

        //We keep track of the user's points
        userScore += input.length() * 5;

        //Checking if the city entered by the player ends with a non-existent letter
        lastUserWordChar = isUsersInvalidLastChar(input);

        Thread.sleep(500);
        computerStep(lastUserWordChar);
    }

    private static boolean isEndFromUser(String input) {
        boolean isEndFromUser = false;

        if (input.equalsIgnoreCase("здаюсь")) {
            isEndFromUser = true;
            gameFrame.dispose(); // Закриття ігрового вікна
            FinalWindow finalWindow = new FinalWindow(); // Відкриття фінального вікна
            finalWindow.whoLoseLabel.setText("Ви здалися.");
            finalWindow.winnerLabel.setText("Перемогу отримав: компьютер");
            finalWindow.userScoreLabel.setText("гравець: " + userScore + " балів");
            finalWindow.computerScoreLabel.setText("комп’ютер: " + computerScore + " балів");
        }
        return isEndFromUser;
    }

    private static boolean isCityInList(String input, HashSet<String> cities, char lastComputerWordChar) {
        boolean isCorrectCity = false;

        for (String city : cities) {
            if (city.equalsIgnoreCase(input)) {
                isCorrectCity = true;
                messageArea.setText("");
                break;
            }
        }
        if (!isCorrectCity) {
            if (lastComputerWord != null){
                messageArea.setText("Такого міста не існує в базі даних. Введіть ще раз місто на лутеру '"
                        + lastComputerWordChar + "'!");
            } else {
                messageArea.setText("Такого міста не існує в базі даних. Введіть інше місто!");
            }
        }
        return isCorrectCity;
    }

    private static boolean isCorrectFirstChar(String input, char lastComputerWordChar) {
        boolean isCorrectFirstChar;

        if (Character.toLowerCase(input.charAt(0)) != Character.toLowerCase(lastComputerWordChar)) {
            isCorrectFirstChar = false;
            messageArea.setText("Ви ввели місто з неправильної першої літери. Введіть ще раз місто на літеру '"
                    + lastComputerWordChar + "'!");
        } else {
            messageArea.setText("");
            isCorrectFirstChar = true;
        }
        return isCorrectFirstChar;
    }

    private static boolean isRepeatedByUser(String input, List<String> usedCities, char lastComputerWordChar) {
        boolean isRepeatedByUser = false;
        if (usedCities.contains(input)) {
            isRepeatedByUser = true;
            messageArea.setText("Таке місто вже було. Введіть інше місто на літеру '" + lastComputerWordChar + "'!");
        }
        return isRepeatedByUser;
    }

    private static char isUsersInvalidLastChar(String input) {
        char lastUserWordChar = Character.toLowerCase(input.charAt(input.length() - 1));
        if (lastUserWordChar == 'ь' || lastUserWordChar == 'и' || lastUserWordChar == 'й' || lastUserWordChar == 'ї') {
            messageArea.setText("Так як немає міста на літеру '" + lastUserWordChar
                    + "', я ввів місто на літеру 'і'.");
            lastUserWordChar = 'І';
        }
        return lastUserWordChar;
    }
}

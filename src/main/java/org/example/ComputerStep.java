package org.example;

import java.util.*;
import java.util.List;

import static org.example.GameWindow.*;
import static org.example.GreatingWindow.cities;
import static org.example.UserStep.userScore;

public class ComputerStep {
    static int computerScore = 0;
    static char lastComputerWordChar;
    public static void computerStep(char lastUserWordChar) {

        //Computer step.
        String lastComputerWord = getRandomCity(lastUserWordChar, cities, usedCities);
        usedCities.add(lastComputerWord);

        computerWordToGameWindow(lastComputerWord);

        //isEnd from computer.
        isEndFromComputer(lastComputerWord);

        //Get computer count.
        computerScore += (lastComputerWord != null ? lastComputerWord.length() * 5 : 0);

        //Check if computer put on the unreal literal
        isComputerInvalidLastChar(Objects.requireNonNull(lastComputerWord));
    }

    private static String getRandomCity(char lastUserWordChar, HashSet<String> cities, List<String> usedCities) {
        List<String> properCities = new ArrayList<>();
        properCities.removeIf(usedCities::contains);
        for (String city : cities) {
            if (Character.toLowerCase(city.charAt(0)) == Character.toLowerCase(lastUserWordChar)) {
                properCities.add(city);
            }
        }

        //Computer check for duplicates.

        if (properCities.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int index = random.nextInt(properCities.size());
        lastComputerWord = properCities.get(index);
        return lastComputerWord;
    }

    private static void computerWordToGameWindow(String lastComputerWord) {
        userWordField.setText(""); //Очищаємо поле для користувача.
        computerWordLabel.setText("" + lastComputerWord); // Відображаємо хід компьютера в вікні.
    }

    private static void isEndFromComputer(String lastComputerWord) {

        if (lastComputerWord == null) {
            gameFrame.dispose(); // Закриття ігрового вікна
            FinalWindow finalWindow = new FinalWindow(); // Відкриття фінального вікна
            finalWindow.whoLoseLabel.setText("Комп'ютер здався.");
            finalWindow.winnerLabel.setText("Перемогу отримав: гравець");
            finalWindow.userScoreLabel.setText("гравець: " + userScore + " балів");
            finalWindow.computerScoreLabel.setText("комп’ютер: " + computerScore + " балів");
        }
    }

    private static void isComputerInvalidLastChar(String lastComputerWord) {
        lastComputerWordChar = Character.toLowerCase(lastComputerWord.charAt(lastComputerWord.length() - 1));
        if (lastComputerWordChar == 'ь' || lastComputerWordChar == 'и' || lastComputerWordChar == 'й'
                || lastComputerWordChar == 'ї') {
            messageArea.setText("Так як немає міста на літеру '" + lastComputerWordChar
                    + "', то введіть місто на літеру 'і'.");
            lastComputerWordChar = 'І';
        }
    }
}

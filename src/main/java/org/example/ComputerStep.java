package org.example;

import java.util.*;
import java.util.List;

import static citiesgame.GameWindow.*;
import static citiesgame.GreatingWindow.cities;
import static citiesgame.UserStep.userScore;

public class ComputerStep {
    static int computerScore = 0;
    static char lastComputerWordChar;
    public static void computerStep(char lastUserWordChar) {

        //Хід компьютера.
        String lastComputerWord = getRandomCity(lastUserWordChar, cities, usedCities);
        usedCities.add(lastComputerWord);

        computerWordToGameWindow(lastComputerWord);

        //isEnd від комп'ютера.
        isEndFromComputer(lastComputerWord);

        //Ведемо рахунок балів компьютера.
        computerScore += (lastComputerWord != null ? lastComputerWord.length() * 5 : 0);

        //Перевірка, якщо місто, яке ввів комп'ютер, закінчується на неіснуючу літеру.
        isComputerInvalidLastChar(Objects.requireNonNull(lastComputerWord));
    }

    private static String getRandomCity(char lastUserWordChar, HashSet<String> cities, List<String> usedCities) {
        List<String> properCities = new ArrayList<>();
        for (String city : cities) {
            if (Character.toLowerCase(city.charAt(0)) == Character.toLowerCase(lastUserWordChar)) {
                properCities.add(city);
            }
        }

        //Перевірка на повтор від комп'ютера.
        properCities.removeIf(usedCities::contains);

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

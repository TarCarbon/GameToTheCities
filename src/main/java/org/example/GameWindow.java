package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static citiesgame.UserStep.userStep;

public class GameWindow implements ActionListener {
    private static final int WINDOW_WIDTH = 516;
    private static final int WINDOW_HEIGHT = 300;

    private static final int USER_LABEL_X = 36;
    private static final int USER_LABEL_Y = 30;
    private static final int USER_LABEL_WIDTH = 108;
    private static final int USER_LABEL_HEIGHT = 30;

    private static final int USER_WORD_FIELD_X = 170;
    private static final int USER_WORD_FIELD_Y = 30;
    private static final int USER_WORD_FIELD_WIDTH = 225;
    private static final int USER_WORD_FIELD_HEIGHT = 30;

    private static final int USER_STEP_X = 395;
    private static final int USER_STEP_Y = 30;
    private static final int USER_STEP_WIDTH = 80;
    private static final int USER_STEP_HEIGHT = 30;

    private static final int COMPUTER_LABEL_X = 36;
    private static final int COMPUTER_LABEL_Y = 90;
    private static final int COMPUTER_LABEL_WIDTH = 108;
    private static final int COMPUTER_LABEL_HEIGHT = 30;

    private static final int COMPUTER_WORD_LABEL_X = 170;
    private static final int COMPUTER_WORD_LABEL_Y = 90;
    private static final int COMPUTER_WORD_LABEL_WIDTH = 284;
    private static final int COMPUTER_WORD_LABEL_HEIGHT = 30;

    private static final int ATTENTION_X = 36;
    private static final int ATTENTION_Y = 140;
    private static final int ATTENTION_WIDTH = 108;
    private static final int ATTENTION_HEIGHT = 30;

    private static final int SCROLL_PANE_X = 36;
    private static final int SCROLL_PANE_Y = 170;
    private static final int SCROLL_PANE_WIDTH = 440;
    private static final int SCROLL_PANE_HEIGHT = 60;

    static JFrame gameFrame;
    static  String input;
    static String lastComputerWord = null;
    static List<String> usedCities = new ArrayList<>();

    //Створюємо компоненти.
    JLabel computerLabel = new JLabel("Комп’ютер:");
    JLabel userLabel = new JLabel("Гравець:");
    static JTextField userWordField = new JTextField(15);
    static JButton userStep = new JButton("Ваш хід");
    static  JLabel computerWordLabel = new JLabel("");
    JLabel attention = new JLabel("Повідомлення:");
    static JTextArea messageArea = new JTextArea("", 5, 20);
    JScrollPane scrollPane = new JScrollPane(messageArea);

    GameWindow() {

        //Створюємо фрейм и задаємо розміри і положення компонентів.
        gameFrame = new JFrame("Міста!");
        gameFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        userLabel.setBounds(USER_LABEL_X, USER_LABEL_Y, USER_LABEL_WIDTH, USER_LABEL_HEIGHT);
        userWordField.setBounds(USER_WORD_FIELD_X, USER_WORD_FIELD_Y, USER_WORD_FIELD_WIDTH, USER_WORD_FIELD_HEIGHT);
        userStep.setBounds(USER_STEP_X, USER_STEP_Y, USER_STEP_WIDTH, USER_STEP_HEIGHT);
        computerLabel.setBounds(COMPUTER_LABEL_X, COMPUTER_LABEL_Y, COMPUTER_LABEL_WIDTH, COMPUTER_LABEL_HEIGHT);
        computerWordLabel.setBounds(COMPUTER_WORD_LABEL_X, COMPUTER_WORD_LABEL_Y, COMPUTER_WORD_LABEL_WIDTH, COMPUTER_WORD_LABEL_HEIGHT);
        attention.setBounds(ATTENTION_X, ATTENTION_Y, ATTENTION_WIDTH, ATTENTION_HEIGHT);
        scrollPane.setBounds(SCROLL_PANE_X, SCROLL_PANE_Y, SCROLL_PANE_WIDTH, SCROLL_PANE_HEIGHT);

        gameFrame.add(userLabel);
        gameFrame.add(userWordField);
        gameFrame.add(userStep);
        gameFrame.add(computerLabel);
        gameFrame.add(computerWordLabel);
        gameFrame.add(attention);
        gameFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageArea.setLineWrap(true);  // Встановлюємо автоматичний перенос рядків
        messageArea.setWrapStyleWord(true);  // Переносимо цілі слова, а не посередині слова

        // Змінюємо колір фону вікна на жовтий.
        gameFrame.setForeground(Color.BLUE);
        gameFrame.getContentPane().setBackground(Color.YELLOW);

        // Зміна розміру та шрифтів.
        Font font = new Font("Arial", Font.BOLD, 18);
        Font mFont = new Font("Arial", Font.PLAIN, 16);
        Font bFont = new Font("Arial", Font.BOLD, 12);
        userLabel.setFont(font);
        userWordField.setFont(font);
        userStep.setFont(bFont);
        computerLabel.setFont(font);
        computerWordLabel.setFont(font);
        attention.setFont(mFont);
        messageArea.setFont(mFont);

        // Змінюємо колір тексту для JLabel-ів та JButton.
        userLabel.setForeground(Color.BLUE);
        userWordField.setForeground(Color.BLACK);
        userStep.setBackground(Color.BLUE);
        userStep.setForeground(Color.YELLOW);
        computerLabel.setForeground(Color.BLUE);
        computerWordLabel.setForeground(Color.BLACK);
        attention.setForeground(Color.BLUE);
        messageArea.setForeground(Color.BLACK);

        // Отримання розмірів екрану
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Розрахунок координат для вікна, щоб відкрити його посередині екрану
        int x = (screenWidth - WINDOW_WIDTH) / 2;
        int y = (screenHeight - WINDOW_HEIGHT) / 2;
        gameFrame.setLocation(x, y);

        userWordField.addActionListener(this);
        userStep.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        input = userWordField.getText();

        try {
            userStep(input, lastComputerWord);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}


package org.example;

import javax.swing.*;
import java.awt.*;

public class FinalWindow {
    private static final int WINDOW_WIDTH = 516;
    private static final int WINDOW_HEIGHT = 260;

    private static final int WHO_LOSE_LABEL_X = 36;
    private static final int WHO_LOSE_LABEL_Y = 22;
    private static final int WHO_LOSE_LABEL_WIDTH = 440;
    private static final int WHO_LOSE_LABEL_HEIGHT = 30;

    private static final int THANKS_LABEL_X = 36;
    private static final int THANKS_LABEL_Y = 56;
    private static final int THANKS_LABEL_WIDTH = 440;
    private static final int THANKS_LABEL_HEIGHT = 30;

    private static final int WINNER_LABEL_X = 36;
    private static final int WINNER_LABEL_Y = 80;
    private static final int WINNER_LABEL_WIDTH = 440;
    private static final int WINNER_LABEL_HEIGHT = 30;

    private static final int SCORE_LABEL_X = 36;
    private static final int SCORE_LABEL_Y = 120;
    private static final int SCORE_LABEL_WIDTH = 200;
    private static final int SCORE_LABEL_HEIGHT = 30;

    private static final int USER_SCORE_LABEL_X = 36;
    private static final int USER_SCORE_LABEL_Y = 146;
    private static final int USER_SCORE_LABEL_WIDTH = 200;
    private static final int USER_SCORE_LABEL_HEIGHT = 30;

    private static final int COMPUTER_SCORE_LABEL_X = 36;
    private static final int COMPUTER_SCORE_LABEL_Y = 170;
    private static final int COMPUTER_SCORE_LABEL_WIDTH = 200;
    private static final int COMPUTER_SCORE_LABEL_HEIGHT = 30;

    private static final int END_BUTTON_X = 330;
    private static final int END_BUTTON_Y = 142;
    private static final int END_BUTTON_WIDTH = 140;
    private static final int END_BUTTON_HEIGHT = 48;

    JFrame finalFrame;
    JLabel whoLoseLabel;
    JLabel thanksLabel;
    JLabel winnerLabel;
    JLabel scoreLabel;
    JButton endButton;
    JLabel userScoreLabel;
    JLabel computerScoreLabel;

    FinalWindow() {

        //Create components.
        whoLoseLabel = new JLabel();
        thanksLabel = new JLabel("Дякую за гру! Тепер ви краще знаєте міста України.");
        winnerLabel = new JLabel();
        scoreLabel = new JLabel("Рахунок:");
        userScoreLabel = new JLabel();
        computerScoreLabel = new JLabel();
        endButton = new JButton("Завершити");

        //We create a frame and set the dimensions and positions of the components.
        finalFrame = new JFrame("Вітаємо!");
        finalFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        whoLoseLabel.setBounds(WHO_LOSE_LABEL_X, WHO_LOSE_LABEL_Y, WHO_LOSE_LABEL_WIDTH, WHO_LOSE_LABEL_HEIGHT);
        thanksLabel.setBounds(THANKS_LABEL_X, THANKS_LABEL_Y, THANKS_LABEL_WIDTH, THANKS_LABEL_HEIGHT);
        winnerLabel.setBounds(WINNER_LABEL_X, WINNER_LABEL_Y, WINNER_LABEL_WIDTH, WINNER_LABEL_HEIGHT);
        scoreLabel.setBounds(SCORE_LABEL_X, SCORE_LABEL_Y, SCORE_LABEL_WIDTH, SCORE_LABEL_HEIGHT);
        userScoreLabel.setBounds(USER_SCORE_LABEL_X, USER_SCORE_LABEL_Y, USER_SCORE_LABEL_WIDTH, USER_SCORE_LABEL_HEIGHT);
        computerScoreLabel.setBounds(COMPUTER_SCORE_LABEL_X, COMPUTER_SCORE_LABEL_Y, COMPUTER_SCORE_LABEL_WIDTH, COMPUTER_SCORE_LABEL_HEIGHT);
        endButton.setBounds(END_BUTTON_X, END_BUTTON_Y, END_BUTTON_WIDTH, END_BUTTON_HEIGHT);

        finalFrame.add(whoLoseLabel);
        finalFrame.add(thanksLabel);
        finalFrame.add(winnerLabel);
        finalFrame.add(scoreLabel);
        finalFrame.add(userScoreLabel);
        finalFrame.add(computerScoreLabel);
        finalFrame.add(endButton);

        finalFrame.setLayout(null);
        finalFrame.setVisible(true);
        finalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        finalFrame.setForeground(Color.BLUE);
        finalFrame.getContentPane().setBackground(Color.YELLOW);
        whoLoseLabel.setForeground(Color.BLACK);
        thanksLabel.setForeground(Color.BLUE);
        winnerLabel.setForeground(Color.BLUE);
        scoreLabel.setForeground(Color.BLUE);
        userScoreLabel.setForeground(Color.BLACK);
        computerScoreLabel.setForeground(Color.BLACK);
        endButton.setBackground(Color.BLUE);
        endButton.setForeground(Color.YELLOW);

        // Changing font size for Label-ів.
        Font font = new Font("Arial", Font.BOLD, 17);
        whoLoseLabel.setFont(font);
        thanksLabel.setFont(font);
        winnerLabel.setFont(font);
        scoreLabel.setFont(font);
        userScoreLabel.setFont(font);
        computerScoreLabel.setFont(font);

        // Changing the font size for startButton.
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        endButton.setFont(buttonFont);

        // Obtaining screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Calculating coordinates to open the window in the middle of the screen.
        int x = (screenWidth - WINDOW_WIDTH) / 2;
        int y = (screenHeight - WINDOW_HEIGHT) / 2;
        finalFrame.setLocation(x, y);

        endButton.addActionListener(e -> {
            finalFrame.dispose(); // Closing the final window.
            System.exit(0);
        });
    }
}

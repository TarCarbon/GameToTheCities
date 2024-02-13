package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class GreatingWindow {
    private static final int WINDOW_WIDTH = 516;
    private static final int WINDOW_HEIGHT = 200;

    private static final int LABEL1_X = 40;
    private static final int LABEL1_Y = 40;
    private static final int LABEL2_X = 40;
    private static final int LABEL2_Y = 82;
    private static final int LABEL_WIDTH = 300;
    private static final int LABEL_HEIGHT = 30;

    private static final int STUDY_BUTTON_X = 320;
    private static final int STUDY_BUTTON_Y = 30;
    private static final int STUDY_BUTTON_WIDTH = 140;
    private static final int STUDY_BUTTON_HEIGHT = 36;

    private static final int PLAY_BUTTON_X = 320;
    private static final int PLAY_BUTTON_Y = 92;
    private static final int PLAY_BUTTON_WIDTH = 140;
    private static final int PLAY_BUTTON_HEIGHT = 36;

    JFrame greatingFrame;
    static HashSet<String> cities = new HashSet<>();

    GreatingWindow() {

        //Create components
        JLabel greatingLabel1 = new JLabel("Вітаємо вас у грі дитинства");
        JLabel greatingLabel2 = new JLabel("і всіх розумників!");
        JButton studyButton = new JButton("Навчатися");
        JButton playButton = new JButton("Грати");

        //Create frame and set size
        greatingFrame = new JFrame("Вітаємо!");
        greatingFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        greatingLabel1.setBounds(LABEL1_X, LABEL1_Y, LABEL_WIDTH, LABEL_HEIGHT);
        greatingLabel2.setBounds(LABEL2_X, LABEL2_Y, LABEL_WIDTH, LABEL_HEIGHT);
        studyButton.setBounds(STUDY_BUTTON_X, STUDY_BUTTON_Y, STUDY_BUTTON_WIDTH, STUDY_BUTTON_HEIGHT);
        playButton.setBounds(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);

        greatingFrame.add(greatingLabel1);
        greatingFrame.add(greatingLabel2);
        greatingFrame.add(studyButton);
        greatingFrame.add(playButton);

        greatingFrame.setLayout(null);
        greatingFrame.setVisible(true);
        greatingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        greatingFrame.setForeground(Color.BLUE);
        greatingFrame.getContentPane().setBackground(Color.YELLOW);
        studyButton.setBackground(Color.BLUE);
        studyButton.setForeground(Color.YELLOW);
        playButton.setBackground(Color.BLUE);
        playButton.setForeground(Color.YELLOW);
        greatingLabel1.setForeground(Color.BLUE);
        greatingLabel2.setForeground(Color.BLUE);

        // Change size of font for greatingLabel.
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        greatingLabel1.setFont(labelFont);
        greatingLabel2.setFont(labelFont);

        // Change size of font for startButton.
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        studyButton.setFont(buttonFont);
        playButton.setFont(buttonFont);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // open window in the center of screen
        int x = (screenWidth - WINDOW_WIDTH) / 2;
        int y = (screenHeight - WINDOW_HEIGHT) / 2;
        greatingFrame.setLocation(x, y);

        studyButton.addActionListener(e -> {
            try {
                fillCities();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            greatingFrame.dispose(); // Close start window
            new StudyWindow(); // Open play window
        });

        playButton.addActionListener(e -> {
            try {
                fillCities();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            greatingFrame.dispose(); // Close start window
            new GameWindow(); // Open play window
        });
    }

    private static void fillCities() throws FileNotFoundException {
        String fileName = "files/cities.txt";
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader br = new BufferedReader(file);
            Scanner s = new Scanner(br);

            String manyCities;

            while (s.hasNextLine()) {
                manyCities = s.nextLine();
                cities.add(manyCities.strip());
            }
            s.close();

        } catch (IOException e) {
            System.err.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}



package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JButton {

    public ScorePanel(Window window) {

        Font font = new Font("Calibri", Font.PLAIN, 40);

        setBackground(new Color(101, 131, 141));
        setSize(600, 60);
        setLocation((window.getWidth() - getWidth()) / 2, 40);
        setFocusable(false);
        setVisible(true);
        setText("Score: " + "0" + " --- Highscore: " + Values.highScore);
        setFont(font);
        setBorder(null);
    }

    public void refreshText() {

        setText("Score: " + Values.score + " " + ">>>Highscore: " + Values.highScore);
    }
}

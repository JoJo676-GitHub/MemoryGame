package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JTextArea {

    public ScorePanel(Window window) {

        Font font = new Font("Calibri", Font.PLAIN, 40);

        setLayout(null);
        setBackground(new Color(101, 131, 141));
        setSize(300, 100);
        setLocation(50, (window.getHeight() - 100) / 2);
        setVisible(true);
        setText("Score: " + "0" + "\n" +
                "Clicks left: " + (Values.tileOrder.size() - Values.selectedTiles.size()));
        setFont(font);
        setEditable(false);
        setBorder(null);
    }
}

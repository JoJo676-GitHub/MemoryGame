package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RestartPanel extends JTextArea {

    Font font = new Font("Calibri", Font.PLAIN, 30);

    public RestartPanel(BackgroundPanel backgroundPanel) {

        setLayout(null);
        setSize(600, 150);
        setLocation((backgroundPanel.getWidth() - 600) / 2, (backgroundPanel.getHeight() - 150) /2);

        setBackground(new Color(101, 131, 141));
        setVisible(true);
        setEditable(false);
        setFont(font);
        setForeground(new Color(65, 9, 9));
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 3));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                backgroundPanel.getLogik().restartGame();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void setNewText() {

        String text = "You failed \n" +
                "Dein Score war " + Values.score;

        if (Values.highScore == Values.score) {
            text += "\nDies ist dein Highscore";
        } else if (Values.highScore > Values.score) {
            text += "\nNoch " + (Values.highScore - Values.score) + " bis du deinen Highscore erreicht hast";
        }

        text += "\nKlicke hier um neu zu starten";

        setText(text);
    }
}

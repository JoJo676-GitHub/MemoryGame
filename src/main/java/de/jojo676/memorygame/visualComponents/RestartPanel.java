package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.MemoryGame;
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
        setLocation((backgroundPanel.getWidth() - 600) / 2, (backgroundPanel.getHeight() - 150) / 2);

        setBackground(new Color(101, 131, 141));
        setVisible(true);
        setEditable(false);
        setFont(font);
        setForeground(new Color(65, 9, 9));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 3));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                MemoryGame.getMemoryGame().getLogik().restartGame();
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

        StringBuilder stringBuilder = new StringBuilder("You failed \n" +
                "Dein Score war");
        stringBuilder.append(Values.score);

        if (Values.highScore == Values.score) {
            stringBuilder.append("\nDies ist dein Highscore");
        } else if (Values.highScore > Values.score) {
            stringBuilder.append(String.format("\nNoch %d bis du deinen Highscore erreicht hast", Values.highScore - Values.score));
        }

        stringBuilder.append("\nKlicke hier um neu zu starten");

        setText(stringBuilder.toString());
    }
}

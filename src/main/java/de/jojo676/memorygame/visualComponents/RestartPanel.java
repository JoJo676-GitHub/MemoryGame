package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.MemoryGame;
import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RestartPanel extends JButton {

    Font font = new Font("Calibri", Font.PLAIN, 30);

    public RestartPanel(BackgroundPanel backgroundPanel) {

        setLayout(new FlowLayout());
        setSize(650, 210);
        setLocation((backgroundPanel.getWidth() - getWidth()) / 2, (backgroundPanel.getHeight() - getHeight()) / 2);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setBackground(new Color(101, 131, 141));
        setVisible(true);
//        setEditable(false);
        setFont(font);
        setForeground(new Color(65, 9, 9));
        setBorder(BorderFactory.createLineBorder(new Color(229, 95, 38), 10));
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

        StringBuilder stringBuilder = new StringBuilder("<html>You failed <br>" +
                "Dein Score war ");
        stringBuilder.append(Values.score);

        if (Values.highScore == Values.score) {
            stringBuilder.append("<br>Dies ist dein Highscore");
        } else if (Values.highScore > Values.score) {
            stringBuilder.append(String.format("<br>Noch %d bis du deinen Highscore erreicht hast", Values.highScore - Values.score));
        }

        stringBuilder.append("<br>Klicke hier um neu zu starten<html>");

        setText(stringBuilder.toString());
    }
}

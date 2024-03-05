package de.jojo676.memorygame.visualComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameStartPanel extends JTextField {

    public GameStartPanel(Window window) {

        Font font = new Font("Calibri", Font.PLAIN, 40);

        setLayout(null);
        setBackground(new Color(26, 190, 190));
        setSize(333, 200);

        setLocation((window.getWidth() - 333)/2, (window.getHeight()-200)/2);
        setVisible(true);
        setText("Start Memory Game");
        setFont(font);
        setBorder(null);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                window.startGame();
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

}

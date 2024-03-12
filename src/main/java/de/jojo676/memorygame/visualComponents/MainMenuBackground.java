package de.jojo676.memorygame.visualComponents;

import javax.swing.*;
import java.awt.*;

public class MainMenuBackground extends JPanel {

    boolean start = true;

    public MainMenuBackground() {

        setBounds(0, 0, 1920, 1080);
        setVisible(true);
        setBackground(new Color(0, 0, 0));
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        if (start) {
            for (int x = 0; x < 1920; x++) {
                for (int y = 0; y < 1080; y++) {

                    g2d.setPaint(new Color((int) (y * 0.2), (int) (y * 0.1), (int) (x * 0.1)));
                    g2d.fillRect(x * 1, y * 1, 1, 1);
                }
            }
        }
    }
}

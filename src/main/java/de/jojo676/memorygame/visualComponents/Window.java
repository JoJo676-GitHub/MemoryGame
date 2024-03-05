package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.SoundEffects;
import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final ScorePanel scorePanel;
    private GameStartPanel gameStartPanel;
    private BackgroundPanel backgroundPanel;
    private SoundEffects soundEffects;

    public ScorePanel getScorePanel() {

        return scorePanel;
    }

    public SoundEffects getSoundEffects() {

        return soundEffects;
    }

    public Window() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        setSize(screenSize);
        setVisible(true);
        getContentPane().setBackground(new Color(50, 60, 76));

        backgroundPanel = new BackgroundPanel(this);
        scorePanel = new ScorePanel(this);
        gameStartPanel = new GameStartPanel(this);
        soundEffects = new SoundEffects();
        add(gameStartPanel);

        repaint();
    }

    public void startGame() {
        remove(gameStartPanel);
        soundEffects.noteGameStart();
        add(backgroundPanel);
        add(scorePanel);
        Values.run = true;
        repaint();
    }
}

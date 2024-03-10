package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.MemoryGame;
import de.jojo676.memorygame.SoundEffects;
import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Window extends JFrame {

    private ScorePanel scorePanel;
    private GameStartPanel gameStartPanel;
    private BackgroundPanel backgroundPanel;
    private SoundEffects soundEffects;

    public ScorePanel getScorePanel() {

        return scorePanel;
    }

    public BackgroundPanel getBackgroundPanel() {

        return backgroundPanel;
    }

    public Window() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(50, 60, 76));
    }

    public void createComponents() {

        backgroundPanel = new BackgroundPanel(this);
        scorePanel = new ScorePanel(this);
        gameStartPanel = new GameStartPanel(this);
        soundEffects = MemoryGame.getMemoryGame().getSoundEffects();
        add(gameStartPanel);
        setVisible(true);
        repaint();
    }

    public void startGame() {

        int delay = 1;
        remove(gameStartPanel);
        if (Values.doStartMelody) {
            soundEffects.noteGameStart();
            delay = 3100;
        }

        Values.executor.schedule(() -> {
            add(backgroundPanel);
            add(scorePanel);
            Values.run = true;
            repaint();
            Values.executor.schedule(() -> MemoryGame.getMemoryGame().getLogik().showNewTilesOrder(), 300, TimeUnit.MILLISECONDS);
        }, delay, TimeUnit.MILLISECONDS);
    }
}

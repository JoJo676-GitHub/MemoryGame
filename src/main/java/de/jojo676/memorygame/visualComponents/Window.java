package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.MemoryGame;
import de.jojo676.memorygame.SoundEffects;
import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class Window extends JFrame {

    private ScorePanel scorePanel;
    private GameStartPanel gameStartPanel;
    private BackgroundPanel backgroundPanel;
    private SoundEffects soundEffects;
    private final MainMenuBackground mainMenuBackground = new MainMenuBackground();

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(50, 60, 76));
        setIconImage(new ImageIcon("src/main/resources/img.png").getImage());

    }

    public void createComponents() {

        backgroundPanel = new BackgroundPanel(this);
        scorePanel = new ScorePanel(this);
        gameStartPanel = new GameStartPanel(this);
        soundEffects = MemoryGame.getMemoryGame().getSoundEffects();
        add(gameStartPanel);
        add(mainMenuBackground);
        setVisible(true);
        repaint();
    }

    public void startGame() {

        int delay = 1;
        remove(gameStartPanel);
        remove(mainMenuBackground);
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

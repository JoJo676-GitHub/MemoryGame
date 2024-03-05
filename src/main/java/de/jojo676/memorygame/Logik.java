package de.jojo676.memorygame;

import de.jojo676.memorygame.visualComponents.BackgroundPanel;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Logik {

    private final BackgroundPanel backgroundPanel;

    public Logik(BackgroundPanel backgroundPanel) {

        this.backgroundPanel = backgroundPanel;

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::timedActions, 500, 1000, TimeUnit.MILLISECONDS);
    }

    public void tileOrder() {

        int nextTile = (int) (Math.random() * Values.tilesMax);
        Values.tileOrder.add(nextTile);
    }

    public void timedActions() {

        backgroundPanel.colorTiles();
    }

    public void checkTiles() {

        if (!Values.selectedTiles.isEmpty() && Values.selectedTiles.size() == Values.tileOrder.size()) {

            if (Values.selectedTiles.equals(Values.tileOrder)) {

                Values.waitForTileSelection = false;
                Values.run = false;
                Color color = backgroundPanel.getBackground();
                backgroundPanel.setBackground(new Color(170, 252, 170, 255));

                Values.score = Values.selectedTiles.size();
                backgroundPanel.getWindow().getScorePanel().setText("Score: " + Values.score + "\n" +
                        "Clicks left: " + (Values.tileOrder.size() + 1));
                backgroundPanel.getWindow().getSoundEffects().noteWin();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                backgroundPanel.setBackground(color);

            } else {
                backgroundPanel.setBackground(new Color(229, 38, 38));
                Values.run = false;
                backgroundPanel.showRestartPanel();
                backgroundPanel.getWindow().getSoundEffects().noteFail();
            }

            if (Values.score > Values.highScore) {
                Values.highScore = Values.score;
            }
            Values.selectedTiles.clear();
            Values.run = true;
        }
    }

    public void restartGame() {

        backgroundPanel.restart();
        Values.tileOrder.clear();
        Values.score = 0;
        Values.waitForTileSelection = false;
        Values.run = true;
    }
}

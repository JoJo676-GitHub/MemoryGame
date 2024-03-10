package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.Logik;
import de.jojo676.memorygame.MemoryGame;
import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.Window;
import java.util.concurrent.TimeUnit;

public class BackgroundPanel extends JPanel {

    private final MemoryTile[] memoryTiles = new MemoryTile[Values.tilesMax];
    private final RestartPanel restartPanel;
    private final Color color;

    public BackgroundPanel(Window window) {

        setLayout(null);
        setBackground(new Color(101, 131, 141));
        setSize(920, 920);
        setLocation((window.getWidth() - getWidth()) / 2, 50);
        setVisible(true);

        color = getBackground();
        restartPanel = new RestartPanel(this);

        addTiles();

        MemoryGame.getMemoryGame().getLogik().showNewTilesOrder();
    }

    public void addTiles() {

        int i = 0;

        for (int j = 0; j < Values.tilesPerLane; j++) {
            for (int k = 0; k < Values.tilesPerLane; k++) {
                memoryTiles[i] = new MemoryTile(i, this);
                memoryTiles[i].setLocation(10 * (k + 1) + (memoryTiles[i].getHeight() * k), 10 * (j + 1) + (memoryTiles[i].getHeight() * j));
                add(memoryTiles[i]);

                i++;
            }
        }
    }

    public void colorTiles(int tileNr) { //Methode soll nur felder färben, wird mit nem fixedRateScheduler aufgerufen, parallel die Musik
        System.out.println("hi");
        Color color = memoryTiles[tileNr].getBackground();
        memoryTiles[tileNr].setBackground(new Color(232, 33, 33)); //Highlight Methode im Tile

        Values.executor.schedule(() -> memoryTiles[tileNr].setBackground(color), 900, TimeUnit.MILLISECONDS);
    }

    public void showRestartPanel() {

        restartPanel.setNewText();
        add(restartPanel, 1);
        repaint();
    }

    public void restart() {

        setBackground(color);
        remove(restartPanel);
    }
}

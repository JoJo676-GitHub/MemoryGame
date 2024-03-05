package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.Logik;
import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private final MemoryTile[] memoryTiles = new MemoryTile[Values.tilesPerLane * Values.tilesPerLane];
    private final Logik logik;
    private final de.jojo676.memorygame.visualComponents.Window window;
    private final RestartPanel restartPanel;
    private final Color color;

    public de.jojo676.memorygame.visualComponents.Window getWindow() {

        return window;
    }

    public Logik getLogik() {

        return logik;
    }

    public BackgroundPanel(Window window) {

        this.window = window;
        setLayout(null);
        setBackground(new Color(101, 131, 141));
        setSize(window.getHeight() - 70, window.getHeight() - 100);
        setLocation((window.getWidth() - window.getHeight()) / 2 + 50, 25);
        setVisible(true);

        color = getBackground();
        logik = new Logik(this);
        restartPanel = new RestartPanel(this);

        addTiles();
    }

    public void addTiles() {
        

        int i = 0;
        int size = (getHeight() - 20) / Values.tilesPerLane;

        for (int j = 0; j < Values.tilesPerLane; j++) {
            for (int k = 0; k < Values.tilesPerLane; k++) {
                memoryTiles[i] = new MemoryTile(i, this);
                memoryTiles[i].setLocation(10 * (k + 1) + (size * k), 10 * (j + 1) + (size * j));
                add(memoryTiles[i]);

                i++;
            }
        }
    }

    public void colorTiles() {

        Color color;

        if (Values.run && !Values.waitForTileSelection) {
            logik.tileOrder();
            for (int tileNr : Values.tileOrder) { //färbt die Tiles der tileOrder nacheinander rot
                color = memoryTiles[tileNr].getColor();
                memoryTiles[tileNr].setBackground(new Color(232, 33, 33));
                window.getSoundEffects().playTileNote(tileNr);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                memoryTiles[tileNr].setBackground(color);
            }
            Values.waitForTileSelection = true;
        } else if (Values.run) {
            logik.checkTiles();

        }
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

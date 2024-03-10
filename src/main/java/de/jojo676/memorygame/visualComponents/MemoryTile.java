package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.MemoryGame;
import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MemoryTile extends JPanel {


    private final Color color;

    public MemoryTile(int nr, BackgroundPanel backgroundPanel) {

        Color colorClicked = new Color(37, 129, 192);

        int size = backgroundPanel.getHeight() - 20;
        size = size - (Values.tilesPerLane - 1) * 10;
        size = size / Values.tilesPerLane;

        setLayout(null);
        setBackground(new Color(89, 181, 238));
        setSize(size, size);
        setVisible(true);
        color = getBackground();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (Values.waitForTileSelection) {

                    MemoryGame.getMemoryGame().getWindow().getScorePanel().refreshText();

                    setBackground(colorClicked);

                    MemoryGame.getMemoryGame().getSoundEffects().playTileNote(nr);

                    executor.scheduleAtFixedRate(tile::undoColor, 300, 1000, TimeUnit.MILLISECONDS);

                    Values.selectedTiles.add(nr);
                    if (Values.selectedTiles.size() == Values.tileOrder.size()) {
                        MemoryGame.getMemoryGame().getLogik().checkTiles();
                    }
                }
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

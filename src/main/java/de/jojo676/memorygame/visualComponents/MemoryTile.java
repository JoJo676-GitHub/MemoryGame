package de.jojo676.memorygame.visualComponents;

import de.jojo676.memorygame.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MemoryTile extends JPanel {

    private final MemoryTile tile;
    private final Color color;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public Color getColor() {

        return color;
    }

    public MemoryTile(int nr, BackgroundPanel backgroundPanel) {

        tile = this;
        int size = (backgroundPanel.getHeight() - 20) / Values.tilesPerLane;

        setLayout(null);
        setBackground(new Color(nr * 6, 245 - ((Values.tilesPerLane * Values.tilesPerLane) - nr / 2) * 13, 12 * nr, 255));
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

                    setBackground(new Color(176, 37, 37));
                    setVisible(true);
                    repaint();
                    backgroundPanel.getWindow().getScorePanel().setText("Score: " + Values.score + "\n" +
                            "Clicks left: " + ((Values.tileOrder.size() - Values.selectedTiles.size()) - 1));

                    setBackground(new Color(color.getRed() / 2, color.getGreen() / 2, color.getBlue() / 2));

                    backgroundPanel.getWindow().getSoundEffects().playTileNote(nr);

                    executor.scheduleAtFixedRate(tile::undoColor, 300, 1000, TimeUnit.MILLISECONDS);

                    Values.selectedTiles.add(nr);
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

    public void undoColor() {

        setBackground(color);
        try {
            executor.awaitTermination(300, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

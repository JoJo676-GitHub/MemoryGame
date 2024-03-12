package de.jojo676.memorygame;

import de.jojo676.memorygame.visualComponents.BackgroundPanel;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Logik {

    private BackgroundPanel backgroundPanel;

    public Logik() {

    }

    public void initializeBackgroundPanel() {

        backgroundPanel = MemoryGame.getMemoryGame().getWindow().getBackgroundPanel();
    }

    public void tileOrder() {

        int nextTile = (int) (Math.random() * Values.tilesMax);
        Values.tileOrder.add(nextTile);
    }

    public void checkTiles() {

        if (!Values.selectedTiles.isEmpty() && Values.selectedTiles.size() == Values.tileOrder.size()) {

            //Trim to size in ein zweites Array
            if (Values.selectedTiles.equals(Values.tileOrder)) {

                Values.waitForTileSelection = false;
                Values.run = false;
                Color color = backgroundPanel.getBackground();
                Values.executor.schedule(() -> {
                    backgroundPanel.setBackground(new Color(50, 180, 50, 255));
                    Values.score = Values.selectedTiles.size();
                    MemoryGame.getMemoryGame().getWindow().getScorePanel().refreshText();
                    MemoryGame.getMemoryGame().getSoundEffects().noteWin(); //MemoryGame.getInstance statisch Variable
                    Values.executor.schedule(() -> {
                        backgroundPanel.setBackground(color);
                        Values.run = true;
                        Values.selectedTiles.clear();
                        showNewTilesOrder();
                    }, 300, TimeUnit.MILLISECONDS);
                }, 800, TimeUnit.MILLISECONDS);
            } else {
                fail();
            }

            if (Values.score > Values.highScore) {
                Values.highScore = Values.score;
            }
        } else if (!Values.selectedTiles.isEmpty()) {
            if (!Values.selectedTiles.equals(Values.tileOrder.subList(0, Values.selectedTiles.size()))) {
                fail();
            }
        }
    }

    public void showNewTilesOrder() {

        if (Values.run && !Values.waitForTileSelection) {
            tileOrder();
            Values.executor.schedule(() -> Values.waitForTileSelection = true, 1000L * Values.tileOrder.size() + 100, TimeUnit.MILLISECONDS);
            ScheduledExecutorService executor2 = Executors.newSingleThreadScheduledExecutor();

            executor2.scheduleAtFixedRate(new Runnable() {
                int tileNr = 0;

                @Override
                public void run() {

                    MemoryGame.getMemoryGame().getSoundEffects().playTileNote(Values.tileOrder.get(tileNr));
                    backgroundPanel.colorTiles(Values.tileOrder.get(tileNr));
                    tileNr++;

                    if (tileNr >= Values.tileOrder.size()) {
                        executor2.shutdown();
//                        Values.waitForTileSelection = true;
                    }
                }
            }, 0, 1000, TimeUnit.MILLISECONDS);
        }
    }

    public void restartGame() {

        backgroundPanel.restart();
        Values.selectedTiles.clear();
        Values.tileOrder.clear();
        Values.score = 0;
        Values.waitForTileSelection = false;
        Values.run = true;
        showNewTilesOrder();
    }

    public void fail() {

        backgroundPanel.setBackground(new Color(229, 95, 38));
        Values.run = false;
        Values.executor.schedule(() -> {
            backgroundPanel.showRestartPanel();
            MemoryGame.getMemoryGame().getSoundEffects().noteFail();
        }, 310, TimeUnit.MILLISECONDS);
    }
}

package de.jojo676.memorygame;

import de.jojo676.memorygame.visualComponents.Window;

public class MemoryGame {

    private static MemoryGame memoryGame;
    private final SoundEffects soundEffects;
    private final Window window;
    private final Logik logik;

    public MemoryGame() {

        memoryGame = this;
        soundEffects = new SoundEffects();
        window = new Window();
        logik = new Logik();
        window.createComponents();
        logik.initializeBackgroundPanel();

    }

    public static MemoryGame getMemoryGame() {

        return memoryGame;
    }

    public SoundEffects getSoundEffects() {

        return soundEffects;
    }

    public Window getWindow() {

        return window;
    }

    public Logik getLogik() {

        return logik;
    }
}

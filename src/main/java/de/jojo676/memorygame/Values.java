package de.jojo676.memorygame;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Values {

    public static int tilesPerLane = 4;//max 6
    public static ArrayList<Integer> tileOrder = new ArrayList<>();
    public static int tilesMax = tilesPerLane * tilesPerLane;
    public static boolean run = false;
    public static boolean waitForTileSelection = false;
    public static ArrayList<Integer> selectedTiles = new ArrayList<>();
    public static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    public static int score = 0;
    public static int highScore = 10;
    public static boolean doStartMelody = false;
}

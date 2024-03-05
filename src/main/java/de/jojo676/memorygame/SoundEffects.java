package de.jojo676.memorygame;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SoundEffects {

    private Synthesizer synthesizer;
    private MidiChannel[] channels;
    private final int[] tonleiter = {
            59, //H
            60, //C
            62, //D
            64, //E
            65, //F
            67, //G
            69, //A
            71, //H
            72, //C
            74, //D
            76, //E
            77, //F
            79, //G
            81, //A
            83, //H
            84, //C
            86, //D
            88, //E
            89, //F
            91, //G
            93, //A
            95, //H
            96, //C
    };

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public SoundEffects() {

        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();
            channels[1].programChange(98);// 79 normal mit hall, 30 start sound, 10: sehr beruhigend 98
            channels[2].programChange(30);
            channels[3].programChange(10);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playTileNote(int nr) {

        channels[1].noteOn(tonleiter[nr], 300);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channels[1].noteOff(tonleiter[nr], 50);
    }

    public void noteGameStart() {

        for (int i = 0; i < 3; i++) {
            channels[2].noteOn(72, 300);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channels[2].noteOff(72, 300);
        }
        channels[2].noteOn(79, 300);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channels[2].noteOff(79, 300);
    }

    public void noteWin() {

        channels[3].noteOn(100, 300);

//        executor.scheduleAtFixedRate(this::allNotesOff, 500, 10000, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channels[3].noteOff(100, 300);
    }

    public void noteFail() {

        channels[2].noteOn(52, 300);

//        executor.scheduleAtFixedRate(this::allNotesOff, 300, 10000, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channels[2].noteOff(52, 50);

        channels[2].noteOn(40, 300);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channels[2].noteOff(40, 300);
    }

    public void allNotesOff() {
        channels[1].allNotesOff();
        channels[2].allNotesOff();
        channels[3].allNotesOff();
        try {
            executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void playAllInstruments() {

        for (int i = 0; i < 128; i++) {
            System.out.println(i);
            channels[2].programChange(i);
            channels[2].noteOn(79, 300);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channels[2].noteOff(79);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

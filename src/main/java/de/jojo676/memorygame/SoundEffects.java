package de.jojo676.memorygame;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.concurrent.TimeUnit;

public class SoundEffects {

    private final MidiChannel[] channels;

    public SoundEffects() {

        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();
            channels[1].programChange(98);// 79 normal mit hall, 30 start sound, 10: sehr beruhigend 98
            channels[2].programChange(30);
            channels[3].programChange(10);
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void playTileNote(int nr) {

        int note = calculateTone(nr);

        Values.executor.execute(() -> channels[1].noteOn(note, 300));

        Values.executor.schedule(() -> channels[1].noteOff(note, 50), 300, TimeUnit.MILLISECONDS);
    }

    public void noteGameStart() {

        for (int i = 0; i < 3; i++) {
            Values.executor.schedule(() -> channels[2].noteOn(72, 300), 700 * i + 1, TimeUnit.MILLISECONDS);
            Values.executor.schedule(()-> channels[2].noteOff(72, 300), 700 * (i+1), TimeUnit.MILLISECONDS);
        }
        Values.executor.schedule(() -> channels[2].noteOn(79, 300), 2100, TimeUnit.MILLISECONDS);

        Values.executor.schedule(() -> channels[2].noteOff(79, 300), 3100, TimeUnit.MILLISECONDS);

    }

    public void noteWin() {

        Values.executor.execute(() -> channels[3].noteOn(100, 300));//führt die Methode sofort aus, nur ein Ding kann gleichzeitig gestartet werden

        Values.executor.schedule(() -> channels[3].noteOff(100, 300), 500, TimeUnit.MILLISECONDS);
    }

    public void noteFail() {

        channels[2].noteOn(52, 300);

        Values.executor.schedule(() -> {
            channels[2].noteOff(52, 50);
            channels[2].noteOn(40, 300);
        }, 300, TimeUnit.MILLISECONDS); //lambda; schöner, praktischer, Finntauglicher

        Values.executor.schedule(() -> channels[2].noteOff(40, 300), 1300, TimeUnit.MILLISECONDS);
    }

    public int calculateTone(int nr) {

        int firstNote = 48; //C
        int note = nr % 7;
        int finalNote;
        int octave = nr / 7;
        switch (note) {
            case 0 -> finalNote = 0;
            case 1 -> finalNote = 2;
            case 2 -> finalNote = 4;
            case 3 -> finalNote = 5;
            case 4 -> finalNote = 7;
            case 5 -> finalNote = 9;
            case 6 -> finalNote = 11;
            default -> finalNote = 1;
        }
        finalNote = (finalNote + 12 * octave) + firstNote;
        return finalNote;
    }
}

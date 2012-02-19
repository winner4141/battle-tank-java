/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Effects;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import static Global.Constants.*;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Lijun
 */
public class SoundEffect {
    private static final int BUFFER_SIZE = 256000;
    
    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public static void PlayExplosion() {
      File soundFile = new File(explosionSound);
            AudioInputStream audioInputStream = null;
            
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
            playAudioStream(audioInputStream, false);

    }

    public static void PlayBackgroundSound() {
        //File soundFile = new File(bgSound);
        try {
                PlayMidi(bgSound, true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//</editor-fold>
    
    //<editor-fold desc="Private methods">
    private static void PlayMidi(String inputfile, boolean continously) {
        try {
            Sequence sequence = MidiSystem.getSequence(new File(inputfile));
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            if (!sequencer.isRunning()) sequencer.start();
            if (continously) sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void playAudioStream(AudioInputStream audioInputStream, boolean continously){
        if (audioInputStream == null) return;
        
        SourceDataLine source = null;
        AudioFormat format = audioInputStream.getFormat();
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            source = (SourceDataLine) AudioSystem.getLine(info);
            source.open(format);
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        source.start();
        
        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                source.write(abData, 0, nBytesRead);
            }
        }

        source.drain();
        source.close();


    }
    //</editor-fold>
}
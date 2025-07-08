/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceattack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

/**
 *
 * @author arlos
 */
//The music class will allow music to be played and stopped
//The audio that it plays consists of 5 songs spanning 28 minuetes total(All in 1 file)
public class Music {

    //The music variable is used to store the file path of the audio track
    private static String music;
    //The clip variable is used to play and execute the audio file
    public static Clip clip;

    //The static void PlayMusic method is used to check if the audio path is 
    //correct(try-catch) and play the music clip
    private static void playMusic() {
        try {

            String songFile = new Music().getSong();

            File musicFile = new File(songFile);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(musicFile));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            FloatControl gainControl = (FloatControl) clip
                    .getControl(FloatControl.Type.MASTER_GAIN);
            double gain = .3D;
            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);

        } catch (Exception i) {
            i.printStackTrace();
        }
    }

    //The String getSong accessor method is used to get the audio file of the song
    public static String getSong() {
        music = "C:\\Users\\arlos\\Documents\\Audacity\\MusicSA.wav";
        return music;

    }

    //The static void method allows the song to be played
    public static void playSong() {
        playMusic();
    }

    //The static void stop method will stop the clip from playing music
    public static void Stop() {
        clip.stop();
    }

    //The static void start method allows the clip to start and allows music to play
    public static void Start() {
        clip.start();
    }

}

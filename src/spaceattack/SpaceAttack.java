/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceattack;

/**
 *
 * @author arlos
 */
public class SpaceAttack {
    //This is the main method to the Space Attack program
    //This method has a runnable/executable instance to start the 
    //excecution of the program

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //The runnable sequence contains, a new instance of the welcome screen,
        //set visible to true, resizible to false and also starts to play music
        //from the music class.
        WScreen start = new WScreen();
        start.setVisible(true);
        start.setResizable(false);
        Music m = new Music();
        m.playSong();

    }

}

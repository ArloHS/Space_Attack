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
public class TempStorage {

    //The TempStorage class stores temporary variables for the current user
    //in order to get the respective data to the respective classes
    //so that the user data can be interpretted
    //The Name variable is gotten from the "Play" Jframe when the player 
    //correctly enters their name. 
    //The Score and Time variables are stored when the user "dies" in the game
    //and when the "GameOver" method is called in the "GameSA" class
    public static String Name = "";
    public static int Score = 0;
    public static String Time = "";
}

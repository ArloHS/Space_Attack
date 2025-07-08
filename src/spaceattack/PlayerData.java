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
public class PlayerData {

    //The PlayerData class creates an object variable that stores 
    //4 data values as seen below
    private String Name;
    private String Time;
    private int Score;
    private String Date;

    //The constructor requires 4 data inputs 
    public PlayerData(String N, String T, int S, String D) {
        Name = N;
        Time = T;
        Score = S;
        Date = D;

    }

    //The followng are accessor methods that return a specific playerdata value 
    public String getName() {
        return Name;
    }

    public String getTime() {
        return Time;
    }

    public int getScore() {
        return Score;
    }

    public String getDate() {
        return Date;
    }

    //The toString method displays the players' data
    public String toString() {

        return Name + "\t" + Time + "\t" + Score + "\t" + Date;
    }
}

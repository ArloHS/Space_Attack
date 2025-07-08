/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceattack;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author arlos
 */
public class DatabaseReader {

    //The Databasereader class creates an array of 1000 possible PlayerData objects
    //It also allows for the data to be sorted and interpreted
    //Array of player data objects
    private PlayerData player[] = new PlayerData[1000];
    //Counter used to keep track of the amount of players
    private int Pcount = 0;

    public DatabaseReader() throws IOException {

        //The Scanner class is used to read data from a text file called "DatabaseSA"
        Scanner scan = new Scanner(new File("DatabaseSA.txt"));
        scan.nextLine();

        //The Scanner scans the text file line for line as one line represents one set 
        //of the player objects' data
        while (scan.hasNext()) {

            String line = scan.nextLine();
            Scanner Line = new Scanner(line).useDelimiter("#");
            //The Scanner puts and uses one line of the databases content to create a playerdata object 
            player[Pcount] = new PlayerData(Line.next(), Line.next(), Line.nextInt(), Line.next());
            //Counter incremented to keep track of how many playerdata objects there are
            Pcount++;
        }

    }

    //The Show accessor method returns the array of player data objects and returns a string value.
    //Each line this method return is a different playerdata object
    public String Show() {
        String temp = "";
        for (int i = 0; i < Pcount; i++) {
            temp += player[i].toString() + "\n";
        }
        return temp;
    }

    //All of the below methods are used to sort the data in various ways using selection sort.
    //the Decencing accessor method returns the data sorted in descending data according to the score 
    public String Descending() {
        String Temp = "";
        for (int i = 0; i < Pcount - 1; i++) {
            for (int j = i + 1; j < Pcount; j++) {
                if (player[i].getScore() < player[j].getScore()) {
                    PlayerData s = player[i];
                    player[i] = player[j];
                    player[j] = s;

                }

            }
            Temp += player[i].toString() + "\n";
        }
        Temp += player[Pcount - 1].toString() + "\n";
        return Temp;

    }

    //the Ascending accessor method returns the data sorted in ascending data according to the score 
    public String Ascending() {
        String Temp = "";
        for (int i = 0; i < Pcount - 1; i++) {
            for (int j = i + 1; j < Pcount; j++) {
                if (player[i].getScore() > player[j].getScore()) {
                    PlayerData s = player[i];
                    player[i] = player[j];
                    player[j] = s;

                }

            }
            Temp += player[i].toString() + "\n";
        }
        Temp += player[Pcount - 1].toString() + "\n";
        return Temp;

    }

    //the Search accessor method returns the data if it is equal to a specific Name
    public String Search(String name) {
        String temp = "";
        for (int i = 0; i < Pcount; i++) {
            if (player[i].getName().equalsIgnoreCase(name)) {
                temp += player[i].toString() + "\n";
            }

        }
        return temp;

    }

    //the Alphabetical accessor method returns the data sorted in alphabetical order according to Name 
    public String Alphabetical() {
        String Temp = "";
        for (int i = 0; i < Pcount - 1; i++) {
            for (int j = i + 1; j < Pcount; j++) {
                if (player[i].getName().compareTo(player[j].getName()) > 1) {
                    PlayerData s = player[i];
                    player[i] = player[j];
                    player[j] = s;

                }

            }
            Temp += player[i].toString() + "\n";
        }
        Temp += player[Pcount - 1].toString() + "\n";
        return Temp;

    }

    //the Recent accessor method returns the data sorted according to the most recent date according to Date
    public String Recent() {
        String Temp = "";
        for (int i = 0; i < Pcount - 1; i++) {
            for (int j = i + 1; j < Pcount; j++) {

                PlayerData s = player[i];
                player[i] = player[j];
                player[j] = s;

            }
            Temp += player[i].toString() + "\n";
        }
        Temp += player[Pcount - 1].toString() + "\n";
        return Temp;

    }

}

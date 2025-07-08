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
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

//The player class inherites from the Sprite class
public class Player extends Sprite {

    //The constructor requires no parameteres and allocates an image object to
    //the player
    public Player() {

        String playerImg = "C:\\Users\\arlos\\Pictures\\New folder\\SASHIP.png";
        ImageIcon ii = new ImageIcon(playerImg);
        setImage(ii.getImage());

    }

}

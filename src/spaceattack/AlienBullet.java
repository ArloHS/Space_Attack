/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceattack;

import javax.swing.ImageIcon;

/**
 *
 * @author arlos
 */
//The alien class inherites from the Sprite class
public class AlienBullet extends Sprite {

    //The destroyed variable checks if the bullet is alive or not
    private boolean destroyed;

    //The constructor requires an x and y co-ordinant to plot its location
    public AlienBullet(int x, int y) {
        //setdestroyed is true until made undestroyed whilst in use
        setDestroyed(true);

        this.x = x;
        this.y = y;

        //The image for the bullet is loaded
        String AlienBulletimg = "C:\\Users\\arlos\\Pictures\\New folder\\AlienBulletSA.png";
        ImageIcon ii = new ImageIcon(AlienBulletimg);
        setImage(ii.getImage());

    }

    //This boolean accessor returns the destroyed variable 
    public boolean isDestroyed() {

        return destroyed;
    }

    //This void method requires a boolean perameter and sets the desytoyed 
    //variable to the parameter 
    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceattack;

import javax.swing.ImageIcon;

//The Shot class is an object that inherites from the Sprite class
//This class will be used to creat bullet objects that the user will shoot
public class Shot extends Sprite {

    //There are two constructors, 1 to inisialise the class and the other to
    //set parameters and images
    public Shot() {
    }

    //This constructor requires x and y co-ordinant paramters to plot their location
    public Shot(int x, int y) {
        //An image is set to this object
        String shotImg = "C:\\Users\\arlos\\Pictures\\New folder\\PlayerBulletSA.png";
        ImageIcon ii = new ImageIcon(shotImg);
        setImage(ii.getImage());
        //x and y co-ordinates are set specifically to come out of the cannon of the ship
        setX(x + 6);
        setY(y - 1);
    }
}

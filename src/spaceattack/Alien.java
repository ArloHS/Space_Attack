/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceattack;

import javax.swing.ImageIcon;

//The Alien class inherites from the Sprite class
public class Alien extends Sprite {

    //An instance of the AlienBullet class is created
    private AlienBullet AB;

    //This is the constructor of the alien class, it requires x and y  
    //co-ordinantes as well as an image parameter
    public Alien(int x, int y, String img) {

        this.x = x;
        this.y = y;
        //An alien must be able to shoot a bullet from where the alien is currently 
        //located and therefore needs the same x and y perameteres
        AB = new AlienBullet(x, y);

        //Sets the image for the alien
        ImageIcon ii = new ImageIcon(img);
        setImage(ii.getImage());
    }

    //This method returns a new alien bullet per alien.
    //It also uses "AlienBullet" as a data type as it returns an object
    public AlienBullet getAlienBullet() {

        return AB;
    }
}

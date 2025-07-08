/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceattack;

import java.awt.Image;

//The Sprite class is a basic entity class used to set up objects through
//inheritance
//This class creates objects with basic functionality such as movement, setImages
//and whether or not it is visible or not
public class Sprite {

    //The x and y variables are used for movement and to set co-ordinantes 
    //on an x and y axis
    int x, y;

    //The dying and visibility variables work hand in hand to test for if a sprite
    //object is dead or not and visible or not, naturally if a sprite object returns
    //as dead, then the object will not be visible.
    private boolean dying;
    private boolean visible;
    //The image variable uses type Image datatypes to set an image for the object
    private Image image;

    //The constructor of Sprite does not require parameteres and only sets an object visible
    public Sprite() {

        visible = true;
    }

    // following 4 methods are a collection of getters(accessors) and setters.
    //The getters return x and y variables to plot their location.
    //The getters require parameters and set the respective x and y variables to the parameters.
    //These are used to plot the locations of the sprite objects on an x and y axis.
    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getY() {

        return y;
    }

    public int getX() {

        return x;
    }

    //The void die method sets visibility to false
    public void die() {

        visible = false;
    }

    //The void setDying method requires a boolean parameter and sets the dying variable to it
    public void setDying(boolean dying) {

        this.dying = dying;
    }

    //The boolean accessor method return the dying variable
    public boolean isDying() {

        return this.dying;
    }

    //The boolean accessor isVisible method returns the visibility of the object
    public boolean isVisible() {

        return visible;
    }

    //The protected void setVisible method requires a boolean parameter and sets
    //the visible variable to the parameter.
    //The method is protected as it only applies to this class
    protected void setVisible(boolean visible) {

        this.visible = visible;
    }

    //The void setImage method requires an image parameter and sets the image variable to the parameter 
    public void setImage(Image image) {

        this.image = image;
    }

    //The type image method accessor method returns the image of the object
    public Image getImage() {

        return image;
    }
}

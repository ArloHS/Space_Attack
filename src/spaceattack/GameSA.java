package spaceattack;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

//ActionListener listens and refreshes the screen based on a timer
//KeyListener listens to key inputs
public class GameSA extends JPanel implements ActionListener, KeyListener {

    //Timer that ticks every 40 Miliseconds
    //count is incremented every 40 miliseconds
    Timer tm = new Timer(40, this);
    int count = 0;

    //Player Variables that increment based on an action
    //x,y,velX,velY are used to position the player
    int x = 400, y = 450, velX = 0, velY = 0;
    int Score = 0;
    String Time = "";
    int Lives = 3;
    int HighScore;
    String FullTime;
    boolean gameEnd = false;

    //Java.util Timer is declared. This timer will be used to display time
    //and increment score
    java.util.Timer timer = new java.util.Timer();
    int i = 0;
    int time;

    //Alien Constant variables/7 different types of aliens (Images)
    String imgArr[] = {"C:\\Users\\arlos\\Pictures\\New folder\\SAROW1.png",
        "C:\\Users\\arlos\\Pictures\\New folder\\SAROW2.png",
        "C:\\Users\\arlos\\Pictures\\New folder\\SAROW3.png",
        "C:\\Users\\arlos\\Pictures\\New folder\\SAROW4.png",
        "C:\\Users\\arlos\\Pictures\\New folder\\SAROW5.png",
        "C:\\Users\\arlos\\Pictures\\New folder\\SAROW6.png",
        "C:\\Users\\arlos\\Pictures\\New folder\\SAROW7.png"};
    int alienCount = 0;
    int direction = 1;
    int down = 25;

    //Alien change variables
    int addRows = 1;
    int addColumns = 6;
    int change = 1;
    int speedChange = 1;
    int alienBulletspeedChange = 0;

    //Object variables, ArrayLists are used for Alien objects
    private List<Alien> aliens;
    private Shot shot;
    private Shot shot1;
    private Shot shot2;
    private Shot shot3;
    private Shot shot4;
    private Player player;

    //PowerUp Variables
    String PlayerSpeed;
    String PlayerBulletSpeed;
    String PlayerBullet;
    String Health;
    String Immunity;
    String WaveClear;
    boolean PowerUP;
    boolean usePowerUp;
    String PU = "None";
    int SpeedPowerUp = 0;
    int BulletVelPowerUp = 0;
    boolean ClearWavePowerUp = false;
    int num;
    int immuneTime;
    int currentHealth;
    boolean AddBullets = false;
    int AddBulletTime;

    JFrame jf = new JFrame();

    //The constructor of GameSA creates a new Jframe 900/650,
    //sets the size to 900,650, the title to "Space Attack", sets resizable
    //to false, visibility to true.
    public GameSA() {
        runTimer();

        jf.setTitle("Space Attack");
        jf.setSize(900, 650);
        jf.setResizable(false);
        jf.setFocusable(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.add(this);
        tm.start();
        //The jframe adds a keylistener
        jf.addKeyListener(this);
        jf.setFocusable(true);
        jf.setFocusTraversalKeysEnabled(false);
        //An array list of aliens is created
        aliens = new ArrayList<>();
        //A double for loop is used to display the amount of aliens.
        //The constructor starts off with 1 row of 6 aliens
        for (int i = 0; i < addRows; i++) {
            for (int j = 0; j < addColumns; j++) {
                //new aliens are made with respective spacing and x and y values inputted
                Alien alien = new Alien(100 + 55 * j, 10 + 40 * i, imgArr[i]);
                //these aliens are added into the aliens array list
                aliens.add(alien);
                //alien count keeps track of the amount of aliens
                alienCount++;
            }
        }
        //A shot object is initialised.
        shot = new Shot();
        //The shot objects below are used for the "addBullets" powerup.
        shot1 = new Shot();
        shot2 = new Shot();
        shot3 = new Shot();
        shot4 = new Shot();
        //A player object is also initialised in the constructor
        player = new Player();
        //aliencount is equal to the amout of aliens in the arraylist of aliens
        alienCount = aliens.size();

    }

    private void drawPlayer(Graphics g) {
        //The drawPlayer mutator method requires a graphics component parameter
        if (player.isVisible()) {
            //if the player is visible (true), the player object will be drawn
            //At the respective x and y axis. The player object is 75 by 75.
            g.drawImage(player.getImage(), player.getX(), player.getY(), 75, 75, this);
        }

    }

    private void drawAliens(Graphics g) {
        //The mutator void drawAliens method requires a graphics component perameter
        //The for-loop, loops through the amount of aliens in the arraylist
        for (Alien alien : aliens) {
            //The aliens are drawn with respective x and y co-ordinates, 40 by 40
            //The aliens can only be drawn if there are aliens left in the arraylist
            if (alien.isVisible() & aliens.size() > 0) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), 40, 40, this);
            }

        }
    }

    private void drawShot(Graphics g) {
        //The mutator void drawShot method requires a graphics component perameter
        if (shot.isVisible()) {
            //If the bullet is visible(true), it will be drawn. The bullet is 5 by 20
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), 5, 20, this);

        }
        //All if statements below are used for the "addBullets" powerup.
        //if the addBullets powerup is true and the bullets are visible,
        //respective x and y co-ordinantes are set and got to position the 
        //4 new bullest appropriatly
        if (AddBullets == true && shot1.isVisible()) {
            shot1.setY(shot.getY() + 12);
            shot1.setX(shot.getX() - 17);
            g.drawImage(shot.getImage(), shot1.getX(), shot1.getY(), 5, 20, this);
        }
        if (AddBullets == true && shot2.isVisible()) {
            shot2.setY(shot.getY() + 17);
            shot2.setX(shot.getX() - 34);
            g.drawImage(shot.getImage(), shot.getX() - 34, shot.getY() + 17, 5, 20, this);
        }
        if (AddBullets == true && shot3.isVisible()) {
            shot3.setY(shot.getY() + 12);
            shot3.setX(shot.getX() + 17);
            g.drawImage(shot.getImage(), shot.getX() + 17, shot.getY() + 12, 5, 20, this);
        }
        if (AddBullets == true && shot4.isVisible()) {
            shot4.setY(shot.getY() + 17);
            shot4.setX(shot.getX() + 34);
            g.drawImage(shot.getImage(), shot.getX() + 34, shot.getY() + 17, 5, 20, this);
        }
    }

    private void drawAlienShot(Graphics g) {
        //The mutator drawAlienShot requires a graphics component perameter
        for (Alien alien : aliens) {
            //The for-loop loops through the amount of aliens
            //A new alien bullet is allocated per alien drawn
            AlienBullet AlienBullet = alien.getAlienBullet();

            if (!AlienBullet.isDestroyed()) {
                //if the alien is not destroyed, the bullet will be drawn, 5 by 20
                g.drawImage(AlienBullet.getImage(), AlienBullet.getX(), AlienBullet.getY(), 5, 20, this);

            }
        }
    }

    public void paintComponent(Graphics g) {
        //The paintComponent method where all components are drawn and displayed on the
        //jframe. The paint component method requires a graphics component.
        //The super.paintcomponent inherites the constructor of the original paint class.
        super.paintComponent(g);

        //The font class is used to integrate "Ariel Black" and change text sizes.
        //The font class helps the drawString methods that desplay the mentioned data
        //Appropriate co-ordinantes and sizes are allocated to the text.
        //Draw Background Board Picture
        ImageIcon h = new ImageIcon("C:\\Users\\arlos\\Pictures\\New folder\\Board1.png");
        Image hh = h.getImage();
        g.drawImage(hh, 0, 0, 900, 650, this);

        //Score Text
        g.setColor(Color.WHITE);
        Font font = new Font("Arial Black", 25, 25);
        g.setFont(font);
        g.drawString("Score: " + Score, 5, 25);

        //Time Text
        g.setColor(Color.WHITE);
        Font font2 = new Font("Arial Black", 25, 25);
        g.setFont(font2);
        g.drawString("Time: " + Time, 725, 55);

        //Lives Text
        g.setColor(Color.WHITE);
        Font font3 = new Font("Arial Black", 25, 25);
        g.setFont(font3);
        g.drawString("Lives: " + Lives, 5, 55);

        //PowerUp Text
        g.setColor(Color.WHITE);
        Font font4 = new Font("Arial Black", 25, 25);
        g.setFont(font4);
        g.drawString("PowerUp: " + PU, 610, 25);

        //Death Line, if aliens reach here the game ends
        g.setColor(Color.RED);
        g.drawLine(0, 525, 900, 525);

        //Draw Component Objects
        drawPlayer(g);
        drawAliens(g);
        drawShot(g);
        drawAlienShot(g);
    }

    public void actionPerformed(ActionEvent e) {
        //The actionPerformed method is a mutator that allows alloactes and 
        //allows for the basic function of the game to occur.
        //An actionevent instance is requires as a perameter.

        //This method is called every 40 miliseconds and is related to the
        //java.swing timer class initialised above.
        //Everytime this method is called, a counter is kept. 
        //This counter is used to allow powerups to be inisialised and used
        count++;
        //A try catch is used to mitigate actionevent and nullpointer errors
        try {
            //Below are all methods created for the programs function (Explained below)
            MoveShip();
            AlienShipDetection();
            GameChange();
            ShootAlienDetection();
            Shoot();
            AlienMovementPattern();
            AlienShootandDetection();
            PowerUp();
            GameOver();
            //Everytime the actionevent method is called, the repaint method is
            //called from the Graphics class. This method repaints the jframe screen
            //Every 40 miliseconds to allow animation to flow and occur
            repaint();
        } catch (NullPointerException d) {;
        }

    }

    public void Shoot() {
        //The void shoot method allows for bullets to be fire if a certain
        //action occurs. 
        //Variable y is used to get the y co-ordinant of the shot.
        int y = shot.getY();
        //Y is incremented by -25 every time the actionevent is called
        y -= 25 + BulletVelPowerUp;

        //If the y co-ordinant is below y level -25, them they will die.
        if (y < -25) {
            shot.die();
            //Shot1-4 is used for the "addBullets" powerup
            shot1.die();
            shot2.die();
            shot3.die();
            shot4.die();
            //The else statement allows the shot objects to increase their y co
            //and appear as a bullet travelling upwards
        } else {
            shot.setY(y);
            shot1.setY(y);
            shot2.setY(y);
            shot3.setY(y);
            shot4.setY(y);
        }
    }

    public void ShootAlienDetection() {
        //The void ShootAliendetection is a method that helps detect if a bullet
        //has collided with an alien
        //The shot x and y variables are used to get the current location of the bullets
        int shotX = shot.getX();
        int shotY = shot.getY();
        //The variables below are used for the "addBullets" powerup, serves the same function
        int shot1X = shot1.getX();
        int shot1Y = shot1.getY();
        int shot2X = shot2.getX();
        int shot2Y = shot2.getY();
        int shot3X = shot3.getX();
        int shot3Y = shot3.getY();
        int shot4X = shot4.getX();
        int shot4Y = shot4.getY();

        //The for-loop loops through all the aliens currently visible
        for (int i = 0; i < aliens.size(); i++) {
            //As the loop, loops, the aliens are gotten from the array list.
            Alien alien = aliens.get(i);
            //The aliens' x and y co-ordinantes are got and put into variables, per alien
            int alienX = alien.getX();
            int alienY = alien.getY();

            //The if-statement below detects collision between the bullets x and y 
            //co and the aliens' x and y co.
            //It also checks if the shot is not dead.
            if (shotX >= (alienX) && shotX <= (alienX + 40) && shotY >= (alienY)
                && shotY <= (alienY + 40) && !shot.isDying()) {
                //if this is true, score is incremented by 5, aliens are removed
                //from the arraylist of aliens, the shot dies and the aliens and
                //bullets are set to dying true(visible false)
                Score += 5;
                alienCount--;
                shot.die();
                shot.setDying(true);
                alien.setDying(true);
                aliens.remove(i);
                break;

            }
            //This if statement is accessed once the addbullets powerup is true
            if (AddBullets == true) {
                //4 other bullets are now checked for collision, exactly
                //like above is done.
                if (shot1X >= (alienX) && shot1X <= (alienX + 40) && shot1Y >= (alienY)
                    && shot1Y <= (alienY + 40) && !shot1.isDying()) {
                    Score += 5;
                    alienCount--;
                    shot1.die();
                    shot1.setDying(true);
                    alien.setDying(true);
                    aliens.remove(i);
                    break;

                }
                if (shot2X >= (alienX) && shot2X <= (alienX + 40) && shot2Y >= (alienY)
                    && shot2Y <= (alienY + 40) && !shot2.isDying()) {
                    Score += 5;
                    alienCount--;
                    shot2.die();
                    shot2.setDying(true);
                    alien.setDying(true);
                    aliens.remove(i);
                    break;

                }
                if (shot3X >= (alienX) && shot3X <= (alienX + 40) && shot3Y >= (alienY)
                    && shot3Y <= (alienY + 40) && !shot3.isDying()) {
                    Score += 5;
                    alienCount--;
                    shot3.die();
                    shot3.setDying(true);
                    alien.setDying(true);
                    aliens.remove(i);
                    break;

                }
                if (shot4X >= (alienX) && shot4X <= (alienX + 40) && shot4Y >= (alienY)
                    && shot4Y <= (alienY + 40) && !shot4.isDying()) {
                    Score += 5;
                    alienCount--;
                    shot4.die();
                    shot4.setDying(true);
                    alien.setDying(true);
                    aliens.remove(i);
                    break;

                }
            }

        }
    }

    public void MoveShip() {
        //the void MoveShip method allows the user to move their ship
        //There are borders on the far left and right of the game screen
        //The if statements below stop movement if this border is reached.
        if (x < 0) {
            velX = 0;
            x = 0;
        }
        if (x > 805) {
            velX = 0;
            x = 805;
        }
        //The x co-ordinant is the x co-ordinant for the players ship.
        //The x co=ordinant is incremented by velX, velx increases everytime
        //The user does an action
        x += velX;
        player.setX(x);
        //The ships x co-ordinant is set.
        player.setY(450);
        //The player may not move in the y axis (Cant move up or down)
        //the y co ordinant is therfore a constant

    }

    public void AlienMovementPattern() {
        //The AlienMovementPattern void method allows the all the aliens to be
        //moved in a pattern at the same time.
        for (Alien alien : aliens) {
            //The for loop loop through all the aliens
            //Off the aliens reach y co-ordinant 500(death line), the game is ended
            if (alien.getY() >= 500) {
                gameEnd = true;
            }
            //The aliens move right (direction =1)
            //The x values are set and gotten to allow direction flow
            alien.setX(alien.getX() + direction);
            //If the aliens reach 700 pixels on the x axis, their direction changes
            //left (direction =-1)
            if (alien.getX() == 700) {
                //speed change is a variable that changes the aliens' speed
                //as the game gets progressively harder (GameChange method)
                direction = -1 * speedChange;
                //When the aliens reach 700 x pixels, they also have to go down
                //The for loop, loops through the aliens and increases their y
                //co-ordinant by 25
                for (Alien a : aliens) {
                    a.setY(a.getY() + down);

                }
                //If the aliens reach 100 x pixels, their direction is changed
                //back to 1 and they move right
            } else if (alien.getX() == 100) {
                direction = 1 * speedChange;
                alien.setX(alien.getX() + direction);
                //Here the aliens once again move down 
                for (Alien a : aliens) {
                    a.setY(a.getY() + down);

                }

            }

        }

    }

    public void AlienShipDetection() {
        //This void method checks if the aliens hit the users ship
        //the users ship variables are stored
        int playerX = player.getX();
        int playerY = player.getY();
        //This for-loop, loops through all the aliens on screen
        for (int i = 0; i < aliens.size(); i++) {
            //They are gotten from the arraylist
            Alien alien = aliens.get(i);
            //The aliens' x and y variables are stored
            int alienX = alien.getX();
            int alienY = alien.getY();
            //This if statement does collision checks between the ship and aliens
            if (playerX + 110 >= (alienX) + 40 && playerX - 40 <= (alienX)
                && playerY + 110 >= (alienY) + 40 && playerY - 40 <= (alienY)) {
                //If they collide, score is increased, the alien/s that collide die
                //and are removed from the array, alien count is lessend and the
                //lives of the player lessens
                Score += 5;
                alien.setDying(true);
                aliens.remove(i);
                Lives--;
                alienCount--;
                break;

            }
        }
    }

    public void GameChange() {
        //The void gamechange method progressively increases the difficulty of the
        //game by increasing the amount of aliens, increasing their speed and bullet speed
        //This method works in waves, everytime a user defeats all the aliens on screen,
        //a new batch of aliens are added with increased difficulty. This change is
        //checked with the "change" variable that keeps track of the aliens per wave
        if (alienCount < 1) {
            change++;
            //Alien rows are added if the change variable is divisible by 2, not by 4
            //There are a maximum of 7 rows of aliens, hence !(change>23)
            if (change % 2 == 0 && change % 4 != 0 && !(change > 23)) {
                addRows++;
            }
            //Aliens columns are added if change is divisible by 3, not 6, not equal to
            //15 or 27 and not added if !(change>34) as only a maximum of 10 columns may appear
            if (change % 3 == 0 && change % 6 != 0 && change != 15 && change != 27 && !(change > 34)) {
                addColumns++;
            }
            //If change is divisible by 4, the aliens' bullet speed will increase by 1
            if (change % 4 == 0) {
                alienBulletspeedChange++;
            }
            //If change is divisible by 6, their speed is increased
            if (change % 6 == 0) {
                speedChange++;
            }
            //If the change variable is equal to itself divisible by six+1, the speed is normal again
            if (change == 7 || change == 13 || change == 19 || change == 25 || change == 33 || change == 37) {
                speedChange = 1;
            }
            //This for-loop re-adds the aliens and their new changed form
            //Similar to the constructor.
            for (int i = 0; i < addRows; i++) {
                for (int j = 0; j < addColumns; j++) {

                    Alien alien = new Alien(200 + 55 * j, 10 + 40 * i, imgArr[i]);
                    aliens.add(alien);
                    alienCount++;
                }
            }

        }
    }

    public void AlienShootandDetection() {
        //AlienShootandDetection, this void method allows the aliens to shoot
        //and do a collision check to see if the alien bullets collide with the 
        //Player
        //A random number instance will determine if the aliens will shoot or not,
        //hence the Random generator variable
        Random generator = new Random();
        //The for loop, loops through all the aliens on screeen
        for (Alien alien : aliens) {
            //Per wave, a random number between 0 and 500 is generated
            int shot = generator.nextInt(500);
            //The getAlienBullet method is called from the alien class
            AlienBullet AB = alien.getAlienBullet();
            //If the random variable =10 or 20, the alien is visible and the bullet
            //is detroyed (true)
            if ((shot == 10 | shot == 20) && alien.isVisible() && AB.isDestroyed()) {
                //The bullet is set destroyed, the bullet is assigned an x and y value
                //is given 
                AB.setDestroyed(false);
                //The + 20 is to position the bullet in the middle of the alien
                AB.setX(alien.getX() + 20);
                AB.setY(alien.getY() + 20);
            }

            //If the bullet is not destroyed, the bullets y increases by 10 
            //pixels, alienBulletSpeedchange occurs in the GameChange method
            if (!AB.isDestroyed()) {

                AB.setY(AB.getY() + 10 + alienBulletspeedChange);

            }
            //Below will be the bullet detection with the ship
            //The aliens' and players x and y values are stored in the following
            //respective varaibles
            int AlienBulletX = AB.getX();
            int AlienBulletY = AB.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            //If the player is visible and the alien is not destroyed
            if (player.isVisible() && !AB.isDestroyed()) {
                //And the aliens' bullet collides with the players' ship, then
                //The bullet is destroyed and the player loses 1 life
                if (AlienBulletX >= (playerX) && AlienBulletX <= (playerX + 75)
                    && AlienBulletY >= (playerY) && AlienBulletY <= (playerY + 75)) {

                    AB.setDestroyed(true);
                    Lives--;

                }
            }
            //if the bullet is nit destroyed
            if (!AB.isDestroyed()) {
                //The bullet is slowed
                AB.setY(AB.getY() + 1);
                //And if the bullet is >= 700 y pixels, the bullet is destroyed
                if (AB.getY() >= 700) {

                    AB.setDestroyed(true);
                }
            }

        }
    }

    public void PowerUp() {
        //The void powerup method allows the user to get powerups throughout the game
        //There are 6 powerups, a random number is chosen every 30 odd seconds
        //It  then chooses a powerup based on the number and a switch statement.
        int ran = (int) (Math.random() * 6) + 1;
        //The count variable counts ticks for the actionperformed class every 40
        //miliseconds, if it is divisible by 700 miliseconds, a powerup text is chosen.
        //This switch statement displays the type of powerup.
        if (count % 700 == 0) {
            num = ran;
            //PU displays text in the powerup draw string mwthod
            switch (num) {
                case 1:
                    PU = "Speed";
                    break;

                case 2:
                    PU = "BulletVel";
                    break;

                case 3:
                    PU = "Health";
                    break;

                case 4:
                    PU = "Immunity";
                    break;

                case 5:
                    PU = "ClearWave";
                    break;

                case 6:
                    PU = "AddBullet";
                    break;
            }
            //Once this is done, a boolean powerup variable is set true
            //(Originally false)
            PowerUP = true;
        }
        //Now, if the users enetr the P key, usepowerup is set to true.
        //If both powerup variables are true, the user is allowed to use the powerup
        if (usePowerUp == true && PowerUP == true) {
            //As the user presses p, PU = none, as the powerup has been used
            switch (num) {
                case 1:
                    //Increases the users x axis speed by 2 (permanent)
                    SpeedPowerUp += 2;
                    PU = "None";
                    break;

                case 2:
                    //Increases the users bullet y axisspeed by 2 (permanent)
                    BulletVelPowerUp += 2;
                    PU = "None";
                    break;

                case 3:
                    //Gives the user 2 extra lives points
                    Lives += 2;
                    PU = "None";
                    break;

                case 4:
                    //stores the users current lives, sets the old lives variable
                    //to 10000 (Only valid for 225 miliseconds)
                    currentHealth = Lives;
                    //A variable is created to stop the immunity powerup if the
                    //timer reaches the immunity time variable
                    immuneTime = count + 225;
                    int immune = 10000;
                    Lives = immune;
                    PU = "None";
                    break;

                case 5:
                    //Clears the current wave from the screen
                    //Sets a boolean value to true (expendable)
                    PU = "None";
                    ClearWavePowerUp = true;
                    break;

                case 6:
                    //This powerup allows the user to shoot 5 bullets at once.
                    //Sets a boolean value to true and creates a variable that
                    //when reached by the timer, will stop the powerup (expendable)
                    PU = "None";
                    AddBullets = true;
                    AddBulletTime = count + 100;
                    break;
            }
            //If the user uses a power up, both booleans are set false until the
            //timer reaches another +- 30 seconds
            usePowerUp = false;
            PowerUP = false;
        }
        //if the timer reaches the the amount of immunity time, the users 
        //lives go back to normal
        if (count == immuneTime) {
            Lives = currentHealth;
        }
        //If the clearwave power up is true, the for loop loops through all the aliens,
        //removes them r=from the arraylist, sets the, dying, decreases the alien count
        //and increases score by 5 per alien cleared
        if (ClearWavePowerUp == true) {
            for (int i = 0; i < aliens.size(); i++) {
                Alien alien = aliens.get(i);
                aliens.remove(i);
                alien.setDying(true);
                alienCount--;
                Score += 5;
            }
        }
        //This if statement helps the above if statement, once the aliens have all
        //been cleared, the powerup returns false
        if (aliens.size() == 0) {
            ClearWavePowerUp = false;
        }
        //If the timer reaches the addbullets time, the add bullets power up
        //is false
        if (count == AddBulletTime) {
            AddBullets = false;
        }

    }

    public void GameOver() {
        //the gameover void method tests to see if the game is over or not
        //if the players lives are 0, the game ends
        //Above in the alien movement pattern, if the aliens reach the death line,
        //the game ends
        if (Lives == 0) {
            gameEnd = true;
        }
        //This if statement stops the game by stopping the timer (This stops the
        //action performed method), stores the current score and time variables and
        //transfers them to the temporary storage class. This screen is set visible
        //to false and a new instance of the game over screen is created. Visible
        //is set to true for the gameover screen. The java,util timer class is also stopped.
        if (gameEnd == true) {
            tm.stop();
            timer.cancel();
            HighScore = Score;
            FullTime = Time;
            TempStorage.Score = HighScore;
            TempStorage.Time = FullTime;
            GameOver gm = new GameOver();
            gm.setVisible(true);
            jf.setVisible(false);
            timer.cancel();

        }
    }

    public void keyPressed(KeyEvent e) {
        //The keypressed void method requires a keyeveet perameter.
        //This method detects keystrokes
        int c = e.getKeyCode();
        //if the user presses A (Left), the users velocity is negatively 
        //increased by 8 pixels
        if (c == KeyEvent.VK_A) {
            //The speed power up adds speed to the velocity (Powerup)
            velX = -8 - SpeedPowerUp;
            velY = 0;
        }
        //if the user presses D (Right), the users velocity 
        //increases by 8 pixels
        if (c == KeyEvent.VK_D) {
            //The speed power up adds speed to the velocity (Powerup)
            velX = 8 + SpeedPowerUp;
            velY = 0;

        }
        //If P is pressed, it sets a powerup key to true
        if (c == KeyEvent.VK_P) {
            usePowerUp = true;
        }

        int xx = x + 29;
        int yy = y;
        //If spacebar is pressed, a new shot variable is created with the ships'
        //current locations as parameters (Because bullets come out of ships)
        if (c == KeyEvent.VK_SPACE) {
            //if the bullet is not already visible it creates the bullet.
            //This allows the user to only shoot again if the current bullet has died.
            if (!shot.isVisible()) {

                shot = new Shot(xx, yy);
            }

        }
        //The following represents the addBullets powerup.
        //if the powerup is true, 4 new bullets are added when shot
        if (AddBullets == true) {
            if (c == KeyEvent.VK_SPACE) {
                shot1 = new Shot();
                shot2 = new Shot();
                shot3 = new Shot();
                shot4 = new Shot();

            }
        }
    }

    public void keyTyped(KeyEvent e) {
        //The keyTyped method is required by the keylistener class.
        //if this method is not coded, an abstract error will occur.
        //for the action listener class to function, all abstract methods must be present.
    }

    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        //The void keyReleased method requires a keyEvent parameter
        //The if statement below allows for the user to shoot and move
        //at the sa,e time.
        if (c != KeyEvent.VK_SPACE) {
            velX = 0;
            velY = 0;
        }
        //if the p key is released, usepowerup =false.
        usePowerUp = false;
    }

    //The timertask is used to task and schedule the timer
    //Variables: i is used to count ticks(Seconds) in the timer, Time is used
    //to display the getTime() method
    TimerTask task = new TimerTask() {
        public void run() {
            i++;
            Time = getTime(i);
            //time = i;
            Score++;
        }
    };

    public void runTimer() {
        //The void runTimer() method is used to schedule the timer every 1000
        //miliseconds or 1 second
        timer.schedule(task, 0, 1000);
    }

    static String getTime(int i) {
        //The static accessor method, getTime() is used to convert the variable of
        //ticks(i) into a string variable that displays the time in the format(00:00)
        int hours = 0;
        int rHours = 0;
        int mins = 0;
        int secs = 0;

        if (i >= 3600) {
            hours = i / 3600;
            rHours = i % 3600;
            if (rHours >= 60) {
                mins = rHours / 60;
                secs = rHours % 60;
            } else {
                secs = rHours;
            }
        } else if (i >= 60) {
            hours = 0;
            mins = i / 60;
            secs = i % 60;
        } else if (i < 60) {
            hours = 0;
            mins = 0;
            secs = i;
        }
        String SHours;
        String SMins;
        String SSecs;

        if (secs < 10) {
            SSecs = "0" + Integer.toString(secs);
        } else {
            SSecs = Integer.toString(secs);
        }
        if (mins < 10) {
            SMins = "0" + Integer.toString(mins);
        } else {
            SMins = Integer.toString(mins);
        }
        if (hours < 10) {
            SHours = "0" + Integer.toString(hours);
        } else {
            SHours = Integer.toString(hours);
        }

        String Time = SMins + ":" + SSecs;
        return Time;

    }
}

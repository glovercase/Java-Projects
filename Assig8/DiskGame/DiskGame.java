// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment
 * Name:Casey Glover
 * Usercode:
 * ID: 300280613
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * A game in which the player has to shoot disks spread out over a shooting range.
 * The gun is stuck on a horizontal line below the shooting range, and shoots upwards.
 * The player can move the gun sideways using the left and right arrow keys, and
 * can fire the gun with the space key.
 * Each shot that hits a disk will damage it some more.
 * When a disk has a high enough damage level, it will explode and disappear.
 * When it explodes it will also damage the disks around it that are close enough.
 * The player has a limited number of shots, (the remaining ammunition is displayed at
 * the side of the window).
 * When the game is over (either the player has run out of ammunition or all the
 * disks have exploded), the score is computed. The score of each disk depends on
 * its damage - the greater the damage, the greater the score.
 */

public class DiskGame implements UIKeyListener, UIButtonListener {
    // Constants for the game geometry: the disks in the shooting range should
    // All be in the rectangle starting at (0,0) with a width of 500 and a height of 150
    // The gun should be on the line at y = 300
    private static final double GAME_WIDTH = 500;
    private static final double SHOOTING_RANGE_Y = 150; // lowest point that a disk can be
    private static final double GUN_Y = 300;
    private static final double GUN = 20;

    //Constants for game logic
    private static final int INITIAL_SHOTS = 30;
    private static final int NUMBER_OF_DISKS = 30;

    //Fields for the game state
    private double gunPosition = GAME_WIDTH/2;  // current x position of the gun
    private double score = 0;                        // current score
    private int shotsRemaining = INITIAL_SHOTS; //How many shots are left
    private int disksRemaining = NUMBER_OF_DISKS;

    private Disk[] disks = new Disk [3];                       //The array of disks

    /**
     * Constructor:
     * Set up the buttons and the keylistener
     * Start the game
     */
    public DiskGame() {
        UI.setKeyListener(this);
        UI.addButton("Restart", this);
        UI.addButton("Left", this);
        UI.addButton("Right", this);
        UI.addButton("FIRE", this); //using a mac so had to add buttons
        UI.addButton("Quit", this);
        this.startGame();
    }

    /**
     * Respond to the buttons
     */
    public void buttonPerformed(String button) {
        if (button.equals("Restart")) {
            startGame();
            UI.println("Click in Graphics Pane");
            UI.println("Then use Arrow keys and space to control gun");
        }
        else if(button.equals("Left")){
            gunPosition -= 10; //the gun moves ten paces to the left
            redraw();
        }else if(button.equals("Right")){
            gunPosition += 10; //the gun moves ten paces to the right
            redraw();
        }else if(button.equals("FIRE")){
            if(shotsRemaining > 0){
                fireShot(); //fire shot method
                redraw();
            }
        }
        else if (button.equals("Quit")) {
            UI.quit();
        }
    }

    /**
     * Respond to the key presses.
     * Left and Right arrows change the gun position;
     * Space fires a shot
     */
    public void keyPerformed(String key) {
        if (key.equals("Right")) {
            /*# YOUR CODE HERE *///moves the gun to the right) USING A MAC SO DIDN"T USE KEYPERFORMED METHOD

        }
        else if (key.equals("Left")) {
            /*# YOUR CODE HERE *///moves the gun to the left

        }
        else if (key.equals("Space")) {
            /*# YOUR CODE HERE *///fires a shot

        }
    }

    /**
     * Set the fields of the game to their initial values,
     * Create a new set of disks
     * redraw the game
     */
    private void startGame() { 
        /*# YOUR CODE HERE */
        //initialise fields
        gunPosition = GAME_WIDTH/2;
        shotsRemaining = INITIAL_SHOTS;
        score = 0; 
        this.initialiseDisks();
        this.redraw();
    }

    /**
     * Make a new array of disks with new disks at random positions
     * within the shooting range.
     * Completion: ensure than none of them are overlapping.
     */
    private void initialiseDisks() {
        /*# YOUR CODE HERE */
        for(int i = 0; i < NUMBER_OF_DISKS; i++){
            do{ //draws a disk in the graphics pane while it puts the disk into the array it checks if it is overlapping
                double x = Math.random() * (GAME_WIDTH - 20) +10; //randon x position
                double y = Math.random() * (SHOOTING_RANGE_Y - 20) + 10; // random y postion
                disks[i] = new Disk(x,y); //places disk in the array
            }  while(checkOverLap(i)); //checking if the disks over lap
            disks[i].draw(); //draw the 30 disks
        }
    }

    private boolean checkOverLap(int index){
        for(int j = 0; j < index; j++){
            if(disks[j].overlapping(disks[index])){ 
                return true; //if one disk overlaps with another it returns true
            }
        }
        return false; //otherwise stay false
    }

    /**
     * The core mechanic of the game is to fire a shot.
     * - Check that there is a shot remaining.
     * - Move the shot up the screen from the gun, step by step, until 
     *   it either goes off the screen or hits a disk.
     *   The shot is constantly redrawn as a line from the gun to its current position.
     * - If the shot hits a disk, it damages the disk, 
     * - If the disk is now broken, then it will damage its neighbours
     *  (ie, all the other disks within range will be damaged also)
     * - If it hit a disk, exit the loop.
     * - Finally, update the score,
     * - If the game is now over,  print out the score 
     * (You should define additional methods - don't do it all in one big method!)
     */
    private void fireShot() {
        if (shotsRemaining < 1 ) { 
            return; 
        }
        shotsRemaining--; //We now have one less shot left
        double shotPosX = gunPosition; //The shot starts at the top of the gun
        double shotPosY = GUN_Y; //The shot starts at the top of the gun
        UI.setColor(Color.black);
        boolean hit = false;
        while (shotPosY > 0 && !hit) { 
            shotPosY -= 20; //speed of the gun shot
            UI.drawLine(shotPosX, GUN_Y, shotPosX, shotPosY);
            //check if it hits a disk... 
            for(Disk d: disks){
                if(d.on(shotPosX,shotPosY)){
                    shotPosX = gunPosition; //sets the shot back to the start
                    diskHit(d);
                    hit = true;
                    this.updateScore();
                    break;
                }
            }
            UI.sleep(1);
        }
        redraw();
        if(shotsRemaining == 1){
            UI.println("You have one more shot!");
        }
        if ((this.allDisksExploded() || this.shotsRemaining < 1)) {
            UI.println("You have a score of " + score);
        }

    }

    public void diskHit(Disk d){
        d.damage(); //damages disk
        if(d.isBroken()){
            d.explode();  //explodes disk
            disksRemaining --; //one less disk
            diskWithinRange(d);
        }
        d.draw(); //draw remaining disks
    }

    public void diskWithinRange(Disk d){
        for(Disk disk: disks){
            if(d.withinRange(disk)){ //if a disk is within range damage that disk
                disk.damage();
                disk.draw(); // draw remaining disk
            }
        }
    }

    public void gun(){ //draws the gun
        UI.setColor(Color.blue);
        UI.fillRect(gunPosition - 10 ,GUN_Y -10,GUN, GUN);
    }

    /**
     * Are all the disks exploded?
     * Useful for telling whether the game is over.
     */
    private boolean allDisksExploded() {
        /*# YOUR CODE HERE */
        //if all disks are exploded tell user game is over

        for(Disk d : disks){
            if(d.isBroken() == false){

                return false;
            }
        }

        return true;
    }

    /**
     * Update the score field, by summing the scores of each disk
     * Score is 150 for exploded disks, 50 for disks with 2 hits, and 20 for disks with 1 hit.
     */
    private void updateScore() {
        score = 0; //set score at zero
        for(Disk d: disks){  
            score += d.score(); //add the score if disk is hit
        }
    }

    /**
     *  Redraws the game:
     *  - Redraws the disks
     *  - Redraws the gun
     *  - Redraws the pile of remaining shot (Completion)
     *  - Displays the current score (Completion)
     *  - calls repaintGraphics to make them visible
     * 
     */
    private void redraw() {
        UI.setImmediateRepaint(false);
        UI.clearGraphics();
        //Redraw the boundary of the shooting range
        UI.setColor(Color.black);
        UI.drawRect(0,0, GAME_WIDTH, GUN_Y);
        UI.setColor(Color.gray);
        UI.drawLine(0, SHOOTING_RANGE_Y, GAME_WIDTH, SHOOTING_RANGE_Y);
        //redraw the gun, the disks, and the pile of remaining rounds, and print score
        if(shotsRemaining > 1){
            UI.clearText(); //clears the text each round
            UI.println("Current score: " +score); //updates the current score
        }
        double posX = 0;
        //make an array of piles loop through array one less shot will take away a pile
        for(int i = shotsRemaining; i > 0 ; i--){ //draws pile of shots 
            posX += 10;
            UI.setColor(Color.black);
            UI.drawRect(posX,400,10,10);
            UI.setColor(Color.green);
            UI.fillRect(posX+1,401,9,9);
        }
        gun(); //calls the gun method
        for(int i = 0; i < NUMBER_OF_DISKS; i++){ //redraws the amount of disks
            disks[i].draw();
        }
        UI.repaintGraphics();
        UI.setImmediateRepaint(true);
    }

    public static void main(String[] args) {
        new DiskGame();
    }

}

// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment
 * Name: Casey Glover
 * Usercode:
 * ID: 300280613
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** Frog
 * A new frog starts at the starting line, in lane 0.
 * It can then hop up to the next lane, or hop back to the previous lane.
 * 
 * The frog must keep track of what lane it is in.
 * It cannot hop back from lane 0.
 *
 * If the frog goes splat, it isn't alive any more, and can't hop.
 * It must keep track of whether it is alive or not.
 *
 * Its x position is given the constant FrogGame.FROG_X
 * Its y position is determined by the lane it is in and constants
 * from the FrogGame class:
 *    y is  FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH
 *
 */

public class Frog {
    // Constants
    public static final int SIZE = 60;
    public static final int posX = 200;  

    // Fields
    private int lane;
    private boolean alive;
    private int posY = 0;

    /*# YOUR CODE HERE */
    //constructor
    /**
     * Make a new frog and draw it.
     */
    public Frog(){
        /*# YOUR CODE HERE */
        lane = 0;
        alive = true;
        posY = 400 - (lane * 100);
        draw();

    }

    public double getX(){
        return posX; //returns position x of the frog
    }

    public double getXMost() {
        return getX() + SIZE; // returns the frog size
    }

    /**
     * Make the frog hop forward: erase, change to the next lane, redraw
     * If the frog is dead it can't move
     * For a nicer version, make the hop in three stages:
     * make the frog spread out its legs, then move, then crouch down again.
     */
    public void hop(){
        /*# YOUR CODE HERE */

        if(alive){
            this.erase();
            posY = posY - 100; //changes the y position 
            lane = lane + 1; // changes the lane number
            this.draw();
        }

    }
    /**
     * Make the frog hop forward: erase, change position, redraw
     * If the frog is dead it can't move.
     * It cannot hop back from lane 0
     * For a nicer version, make the hop in three stages:
     * make the frog spread out its legs, then move, then crouch down again.
     */
    public void hopBack(){
        /*# YOUR CODE HERE */

        if(alive && lane > 0){ 
            this.erase();

            posY = posY + 100; //changes the y position 
            lane = lane - 1; // changes the lane number
            this.draw();
        }

    }

    /**
     * The Frog is hit by a car, and is now dead.
     * It should be redrawn.
     */
    public void splat(){
        /*# YOUR CODE HERE */
        UI.drawImage("splat.jpg", posX, posY, SIZE, SIZE);
        alive = false;

    }

    /**
     * Return the lane the frog is currently in
     */
    public int getLane(){
        /*# YOUR CODE HERE */

        return lane; 
    }

    /**
     * Return true if the frog is alive, and false if it is not
     */
    public boolean isAlive(){
        /*# YOUR CODE HERE */
        return alive; 

    }
        
    /**
     * Draws the frog at the current position.
     * Works out x and y as described in the comment at the top of the file
     * If it is not alive, draws a splat.
     */
    public void draw(){
        /*# YOUR CODE HERE */
        if(alive){

            UI.drawImage("littlefrog.jpg", posX, posY, SIZE, SIZE, false); //draws frog
            UI.repaintGraphics();
        }else{
            UI.drawImage("splat.jpg", posX, posY, SIZE, SIZE, false);
            UI.repaintGraphics();
        }
    }

    /**
     * Erase the frog
     * Works out x and y as described in the comment at the top of the file
     */

    public void erase(){
        /*# YOUR CODE HERE */
        UI.eraseRect( posX, posY, SIZE, SIZE); //erases frog

    }


    /**
     * The main method will test your frog class
     */
    public static void main(String[] args) {
        // draw lanes
        double y = FrogGame.START_LINE-5;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        // make frog hop
        while (true){
            UI.println("Making new frog and making it hop randomly.");
            Frog frog = new Frog();
            UI.sleep(500);
            while(frog.getLane() < 3){
                //make frog hop
                if (Math.random()>0.3){
                    frog.hop();
                }
                else {
                    frog.hopBack();
                }
                UI.sleep(500);
            }                                   
            //make the frog die to allow checking that it doesn't move
            frog.splat();
            if (frog.isAlive()){
                UI.println("Frog shouldn't be alive after being splatted!");
            }
            frog.hop();
            UI.sleep(300);
            frog.hopBack();
            UI.sleep(500);
            UI.println("Did the frog move after being splatted? It shouldn't!");
        }
    }

}

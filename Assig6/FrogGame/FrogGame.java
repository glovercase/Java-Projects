// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** FrogGame
 * The game makes three cars that run along a three lane road
 * The player has two buttons to control a frog that starts on one side (lane 0)
 * and has to hop to the other side of the road (lane 4) without being hit.
 * If the frog gets to the other side, the player gets a point, and a new frog.
 * If a car hits the frog, it goes splat, and the player loses a life.
 * The game is over when the player has no lives left.
 */
public class FrogGame implements UIButtonListener{

    // Constants
    // frog
    public static final double START_LINE = 400;    // the y position of the start line for the frog
    public static final double FROG_X = 200;    // the y position of the start line for the frog

    // roads
    public static final double ROAD_LEFT = 0;      // the left end of the road
    public static final double ROAD_RIGHT = 500;   // the right end of the road
    public static final double LANE_WIDTH = 100;


    // Fields
    private Frog frog;          // The current frog.
     


    /** Construct a new FrogGame object:
     * - set up the GUI (the buttons)
     * - draw the lanes
     * - create the first frog
     * - call the runCars method to make the cars run along the road.
     */
    public FrogGame(){
        /*# YOUR CODE HERE */

    }


    // GUI Methods

    /**
     *  Respond to button presses:
     * "Hop" should make the frog hop forward,
     * "Back" should make the frog hop backwards.
     */
    public void buttonPerformed(String button){
        /*# YOUR CODE HERE */

    }

    /**
     * Draw the lanes.
     */
    public void drawLanes(){
        /*# YOUR CODE HERE */

    }



    /**
     * Initialise variables for the score and the remaining lives
     * Make three cars, and make them run along their lanes
     * until the player has run out of lives.
     * In the loop,
     *  - make each car move, 
     *  - If the frog has got to lane 4, then
     *     add to the score,
     *     tell the player what the score is.
     *     and make a new frog
     *  - check each car to see if it is hitting the frog.
     *    ( call the hitFrog(..) method to check each car )
     *    If so, make the frog splat, decrease the number of lives left.
     *  - If the player still has any lives left, make a new frog.
     *    and make a new frog.
     *  - then sleep for short time.
     */
    public void runCars(){
        /*# YOUR CODE HERE */

    }


    /**
     * Returns true if the car is hitting the frog:
     * the lane of the car must be the same as the current lane of the frog
     * and the x position of the car must be such that it is hitting the frog.
     */
    public boolean hitFrog(Car car){
        /*# YOUR CODE HERE */

        return true;  //remove this line (it is just to allow the template to compile)
    }




    // Main
    public static void main(String[] arguments){
        new FrogGame();
    }   


}

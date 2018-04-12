// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 
 * Name: Casey Glover
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
    public static final double ROAD_MIDDLE = 250; // middle of the road
    public static final double ROAD_RIGHT = 500;   // the right end of the road
    public static final double LANE_WIDTH = 102; // lane width

    // Fie4-lds
    private Frog frog;          // The current frog.
    private int score = 0; // score starts at 0
    private int remainingLives = 5; // lives set at 5
    private int NUM_CARS = 5;

    /** Construct a new FrogGame object:
     * - set up the GUI (the buttons)
     * - draw the lanes
     * - create the first frog
     * - call the runCars method to make the cars run along the road.
     */
    public FrogGame(){
        /*# YOUR CODE HERE */

        UI.addButton("Hop", this); //adds new buttons
        UI.addButton("Back", this);
        UI.addButton("Restart", this);
        frog = new Frog(); //new frog
        this.drawLanes(); //draws lines
        this.runCars(); // runs the cars

    }
    // GUI Methods
    /**
     *  Respond to button presses:
     * "Hop" should make the frog hop forward,
     * "Back" should make the frog hop backwards.
     */
    public void buttonPerformed(String button){
        /*# YOUR CODE HERE */

        if(button.equals("Hop")){ // hop button
            frog.hop();
        } else if(button.equals("Back")){ //back button
            frog.hopBack();
        } else if(button.equals("Restart")){ //restart button
            frog = new Frog();
            remainingLives = 5; //sets new lives to 5
            score = 0; // resets the score to 0
        }

    }

    /**
     * Draw the lanes.
     */
    public void drawLanes(){
        /*# YOUR CODE HERE */ //draws the four lines to make the roads
        UI.drawLine(ROAD_LEFT, START_LINE, ROAD_RIGHT, START_LINE);
        UI.drawLine(ROAD_LEFT, START_LINE - LANE_WIDTH, ROAD_RIGHT, START_LINE - LANE_WIDTH);
        UI.drawLine(ROAD_LEFT, START_LINE - LANE_WIDTH * 2, ROAD_RIGHT, START_LINE - LANE_WIDTH * 2);
        UI.drawLine(ROAD_LEFT, START_LINE - LANE_WIDTH * 3, ROAD_RIGHT, START_LINE - LANE_WIDTH * 3);

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
        //makes new cars direction, position and lane
       
        Car[] cars = {null, null, null, null, null}; // intialise array of cars

        cars[0] = new Car("right", 1, FrogGame.ROAD_LEFT);
        cars[1] = new Car("left", 2, FrogGame.ROAD_RIGHT);
        cars[2] = new Car("right", 3, FrogGame.ROAD_LEFT);
        cars[3] = new Car("right", 1, FrogGame.ROAD_MIDDLE);
        cars[4] = new Car("right", 3, FrogGame.ROAD_MIDDLE);
        
        while (true){ //while the game is running
            UI.sleep(20); //sets sleep for the loop
            for(int i = 0; i < NUM_CARS; i++){ //for loop for cars moving
                cars[i].move();
            }

            
            if(!frog.isAlive() ){ // when the frog is dead
                
                if(remainingLives > 0){
                    frog = new Frog(); // makes a new frog
                }

                continue; // jumps back to the top of the loop

            }
            if(frog.getLane() == 4){

                frog = new Frog(); // makes a new frog after it reaches top
                score = score +10; // add 10 points to score
                UI.println("Your score is " +score);

            }
            for(int i = 0; i < NUM_CARS; i++){
                if (hitFrog(cars[i])) { //calling the hit frog method

                    frog.splat(); // kills the frog

                    remainingLives -= 1; //deducts one life from remaining lives
                    if(remainingLives == 0){
                        UI.println("Game over!!, Click Restart to play again"); //prints game over

                    }else{
                        UI.println("You have " +remainingLives + "left"); //prints score
                    }
                    break; //breaks out of the for loop
                }
            }

            
        }
    }

    /**
     * Returns true if the car is hitting the frog:
     * the lane of the car must be the same as the current lane of the frog
     * and the x position of the car must be such that it is hitting the frog.
     */
    public boolean hitFrog(Car car){
        /*# YOUR CODE HERE */
        if(car.getLane() == frog.getLane()){ // frog is in the same lane as a car
            if (car.getXMost() >= frog.getX() && car.getX() <= frog.getXMost()) { //positioning of frog anc car
                return true; // if the car hits the frog it returns true
            }
        }
        return false;
    }
    //remove this line (it is just to allow the template to compile)
    // Main
    public static void main(String[] arguments){
        new FrogGame();
    }   

}

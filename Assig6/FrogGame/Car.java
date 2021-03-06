// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

import java.awt.Color;

import ecs100.UI;

/**
 * Car:
 * A car drives along one lane of the road, (lane 1, 2, 3, ... )
 * It must keep track of its x position. 
 * Its y position is determined by the lane it is in and constants
 * from the FrogGame class:
 *    y is  FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH
 * 
 * Each car has a direction - going to the left or going to the right.
 * When a car gets to the end of the road it disappears and immediately
 * reappears at the other end, still moving in the same direction.
 * 
 * The ends of the road are defined by the constants:  FrogGame.ROAD_LEFT or FrogGame.ROAD_RIGHT.
 * All cars move at the same speed.
 */

public class Car {

    // Constants  (size of the car)
    public static final int WIDTH = 50;
    public static final int HEIGHT = 30;
    public static final int SPEED = 3;     // pixels to move each tiem move() is called
        
    // Fields
    /*# YOUR CODE HERE */
      private double lane;
      private double posX;
      private string direction = "left";
        
    // Constructor
    /**
     * Make a new car, specifying the direction, the speed, the lane,
     * and the x position in the lane
     */
    public Car(String dir, int lane,  double x ){
        /*# YOUR CODE HERE */

    }
        
    /**
     * Move the car according to its speed and direction:  erase, change position, redraw
     * If it goes past the end of the road, move it to the other end.
     */
    public void move(){
        /*# YOUR CODE HERE */


    }
        
    /**
     * Return the lane the car is in
     */
    public int getLane(){
        /*# YOUR CODE HERE */

        return this.lane;  //remove this line (it is just to allow the template to compile)
    }
        
    /**
     * Return the x position of the car
     */
    public double getX(){
        /*# YOUR CODE HERE */

        return posX;  //remove this line (it is just to allow the template to compile)
    }
        
    /**
     * Draw the car.  The roof portion should be a darker version of the colour of the body.
     */
    public void draw(){
        /*# YOUR CODE HERE */
        UI.fillRect(

    }
        
        
    /**
     * Erase the car from its current position
     */
    public void erase(){
        /*# YOUR CODE HERE */

    }





    /**
     * Test the cars.  Makes three cars, one going right and two going left,
     * then makes the repeatedly move for ever.
     * Prints a message every time the first car is overlapping FrogGame.FROG_X
     */

    public static void main(String[] args){
        // draw the lanes.
        double y = FrogGame.START_LINE-5;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);

        y = y - FrogGame.LANE_WIDTH;
        UI.drawLine(FrogGame.ROAD_LEFT, y,  FrogGame.ROAD_RIGHT, y);


        Car car1 = new Car("right", 1, FrogGame.ROAD_LEFT);
        Car car2 = new Car("left",  2, FrogGame.ROAD_LEFT+100);
        Car car3 = new Car("left", 2, FrogGame.ROAD_RIGHT-70);
        while (true){
            car1.move();
            car2.move();
            car3.move();
            if (car1.getX() <= FrogGame.FROG_X && car1.getX()+WIDTH >= FrogGame.FROG_X ){
                UI.println("Car1 crossing frog route");
            }
            UI.sleep(50);
        }
    }


        
}

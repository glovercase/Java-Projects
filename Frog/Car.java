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
    public static final int SPEED = 6;
    // pixels to move each tiem move() is called
   
        
    // Fields
    /*# YOUR CODE HERE */
     private double posX;
     private double posY;
     private int laneNum;
     private String direction;
     private Color color;
     private Color dcolor;
     
    // Constructor
    /**
     * Make a new car, specifying the direction, the speed, the lane,
     * and the x position in the lane
     */
    public Car(String dir, int lane,  double x ){
        /*# YOUR CODE HERE */
        laneNum = lane;
        direction = dir;
        posX = x;
        float colour = (float)Math.random();
        color = Color.getHSBColor(colour, 1, 1);
        dcolor = Color.getHSBColor(colour, 1, 0.7f);
        posY = FrogGame.START_LINE - lane * FrogGame.LANE_WIDTH + 30;

    }
        
    /**
     * Move the car according to its speed and direction:  erase, change position, redraw
     * If it goes past the end of the road, move it to the other end.
     */
    public void move(){
        /*# YOUR CODE HERE */
        while(true){
        this.erase();
        if (this.direction.equals("left")){
            this.posX = this.posX + this.SPEED;
            this.erase();
            if(this.posX >= FrogGame.ROAD_RIGHT){
                this.posX = FrogGame.ROAD_LEFT;
            }
        }else if (this.direction.equals("right")){
            this.posX = this.posX - this.SPEED;
            this.erase();
            if(this.posX <= FrogGame.ROAD_LEFT){
                this.posX = FrogGame.ROAD_RIGHT;
            }
        }
        this.draw();
        UI.sleep(2);
        UI.repaintGraphics();
        break;
        
    }

    }
        
    /**
     * Return the lane the car is in
     */
    public int getLane(){
        /*# YOUR CODE HERE */

        return laneNum;  //returns lane number
    }
        
    /**
     * Return the x position of the car
     */
    public double getX(){
        /*# YOUR CODE HERE */

        return posX;  //returns position x
    }
    
    public double getXMost(){
        return getX() + WIDTH;
    }
        
    /**
     * Draw the car.  The roof portion should be a darker version of the colour of the body.
     */
    public void draw(){
        /*# YOUR CODE HERE */
        UI.setColor(color); //set color
        UI.fillRect(posX , posY , WIDTH, HEIGHT, false); //fills body of car
       
        UI.setColor(dcolor); //set darker color
        UI.fillRect(posX + WIDTH*1/4, posY + HEIGHT*1/4 , WIDTH*1/2, HEIGHT*1/2, false); //fills roof of car
        
    }
        
        
    /**
     * Erase the car from its current position
     */
    public void erase(){
        UI.eraseRect(posX, posY, WIDTH, HEIGHT, false); //erases image
        
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


        Car car1 = new Car("left", 1, FrogGame.ROAD_LEFT); //changed postions to test cars
        Car car2 = new Car("left",  3, FrogGame.ROAD_LEFT);
        Car car3 = new Car("right", 2, FrogGame.ROAD_RIGHT);
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

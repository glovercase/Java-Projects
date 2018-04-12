// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:Cassey G
 * Usercode:
 * ID:
 */

import java.util.*;
import ecs100.*;
import java.awt.Color;
import java.io.*;


/** Rectangle represents a solid rectangle shape
    Implements the Shape interface.
    Needs fields to record the position, size, and colour*/

public class Rectangle implements Shape {
    //fields
    /*# YOUR CODE HERE */
    private double x;  // one end
    private double y;
    private double wd;  // the other end
    private double ht;
    private Color col;  

    /** Constructor with explicit values
        Arguments are the x and y of the top left corner,
        the width and height, and the color. */
    public Rectangle(double x, double y, double wd, double ht, Color col) {
        /*# YOUR CODE HERE */ //construts a new rectangle with following arguments
        this.x = x; 
        this.y = y; 
        this.wd = wd;
        this.ht = ht;
        this.col = col;
    }

    /** [Completion] Constructor which reads values from a String
        that contains the specification of the Rectangle. 
        The format of the String is determined by the toString method.
        The first 3 integers specify the color;
        the following four numbers specify the position and the size.
        */
    public Rectangle(String description) {
        /*# YOUR CODE HERE */ //constructs a rectangle from a string
        Scanner data = new Scanner(description); //scans the description
        int red = data.nextInt();
        int green = data.nextInt();
        int blue = data.nextInt();
        this.col = new Color(red, green, blue);
        this.x = data.nextDouble();
        this.y = data.nextDouble();
        this.wd = data.nextDouble();
        this.ht = data.nextDouble();
    }


    /** Returns true if the point (u, v) is on top of the shape */
    public boolean on(double u, double v) {
        /*# YOUR CODE HERE */
        return (u > this.x && u < this.x + this.wd && v > this.y && v < this.y + this.ht); //checks the paramerters of the rectangle to see if u and v are within
    }

    /** Changes the position of the shape by dx and dy.
        If it was positioned at (x, y), it will now be at (x+dx, y+dy)*/
    public void moveBy(double dx, double dy) {
        /*# YOUR CODE HERE */
        this.x += dx; //moves x by dx
        this.y += dy; //moves y by dy
    }

    /** Draws the rectangle on the graphics pane. It draws a black border and
        fills it with the color of the rectangle.
        It uses the drawing methods with the extra last argument of "false"
        so that the shape will not actually appear until the 
        graphics pane is redisplayed later. This gives much smoother redrawing.*/
    public void redraw() {
        /*# YOUR CODE HERE */
        UI.setColor(Color.black);
        UI.drawRect(this.x-1,this.y-1,this.wd+1, this.ht+1, false);
        UI.setColor(this.col);
        UI.fillRect(this.x,this.y,this.wd, this.ht, false);
    }


    /** [Completion] Changes the width and height of the shape by the
        specified amounts.
        The amounts may be negative, which means that the shape
        should get smaller, at least in that direction.
        The shape should never become smaller than 1 pixel in width or height
        The center of the shape should remain the same.*/
    public void resize (double changeWd, double changeHt) {
        /*# YOUR CODE HERE */
        
        this.wd += changeWd;
        this.ht += changeHt;
        this.x -= changeWd/2;
        this.y -= changeHt/2;
        if(this.wd < 1){
            this.wd = 1;
        }
        if(this.ht < 1){
            this.ht = 1;
        }
        
    } 
  

    /** Returns a string description of the rectangle in a form suitable for
        writing to a file in order to reconstruct the rectangle later
        The first word of the string must be Rectangle */
    public String toString() {
        /*# YOUR CODE HERE */
        return ("Rectangle "+col.getRed()+" "+col.getGreen()+" "+col.getBlue()+" "+this.x+" "+this.y+" "+this.wd+" "+this.ht);
    }

}

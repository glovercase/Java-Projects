// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:
 * Usercode:
 * ID:
 */

import java.util.*;
import ecs100.*;
import java.awt.Color;
import java.io.*;

/** Oval represents an oval shape
Implements the Shape interface.
Needs fields to record the position, size, and colour
 */

public class Oval implements Shape {
    /*# YOUR CODE HERE */
    private double x;  // one end
    private double y;
    private double wd;  // the other end
    private double ht;
    private Color col;

    /** Constructor with explicit values
    Arguments are the x and y of the top left corner,
    the width and height, and the color.  */
    public Oval (double x, double y, double wd, double ht, Color col){
        /*# YOUR CODE HERE */
        this.x = x;
        this.y = y;
        this.wd = wd;
        this.ht = ht;
        this.col = col;
    }

    /** [Completion] Constructor which reads values from a String
    that contains the specification of the Oval. 
    The format of the String is determined by the toString method.
    The next 3 integers specify the color;
    the following four numbers specify the position and the size.
     */
    public Oval(String description) {
        /*# YOUR CODE HERE */
        Scanner data = new Scanner(description);
        int red = data.nextInt();
        int green = data.nextInt();
        int blue = data.nextInt();
        this.col = new Color(red, green, blue);
        this.x = data.nextDouble();
        this.y = data.nextDouble();
        this.wd = data.nextDouble();
        this.ht = data.nextDouble();
    }

    public double centerX(){
        return (this.x + wd/2);
    }

    public double centerY(){
        return (this.y +ht/2);
    }

    public double xRadius(){
        return wd/2;
    }

    public double yRadius(){
        return ht/2;
    }

    /** Returns true if the point (u, v) is on top of the shape */
    public boolean on(double u, double v){
        // an easy approximation is to pretend it is the enclosing rectangle.
        // It is nicer to do a little bit of geometry and get it right
       return (Math.pow(u-centerX(),2) / Math.pow(xRadius(),2) + Math.pow(v-centerY(),2) / Math.pow(yRadius(),2) < 1);
           
    }

    /** Changes the position of the shape by dx and dy.
    If it was positioned at (x, y), it will now be at (x+dx, y+dy)
     */
    public void moveBy(double dx, double dy){
        /*# YOUR CODE HERE */
        this.x += dx;
        this.y += dy;
    }

    /** Draws the oval on the graphics pane. It draws a black border and
    fills it with the color of the oval.
    It uses the drawing methods with the extra last argument of "false"
    so that the shape will not actually appear until the 
    graphics pane is redisplayed later. This gives much smoother redrawing.
     */
    public void redraw(){
        /*# YOUR CODE HERE */
        UI.setColor(Color.black);
        UI.drawOval(this.x,this.y,this.wd,this.ht,false);
        UI.setColor(this.col);
        UI.fillOval(this.x,this.y,this.wd,this.ht,false);
    }

    /** [Completion] Changes the width and height of the shape by the
    specified amounts.
    The amounts may be negative, which means that the shape
    should get smaller, at least in that direction.
    The shape should never become smaller than 1 pixel in width or height
    The center of the shape should remain the same.
     */
    public void resize(double changeWd, double changeHt){
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

    /** Returns a string description of the oval in a form suitable for
    writing to a file in order to reconstruct the oval later
    The first word of the string must be Oval */
    public String toString(){
        /*# YOUR CODE HERE */
        return ("Oval "+col.getRed()+" "+col.getGreen()+" "+col.getBlue()+" "+this.x+" "+this.y+" "+this.wd+" "+this.ht);
    }

}

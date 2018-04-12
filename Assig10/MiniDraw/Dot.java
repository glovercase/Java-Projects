// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.awt.Color;
import java.io.*;

/** Dot represents a small circle shape of a fixed size (5 pixels)
Implements the Shape interface.
Needs fields to record the position, and colour
 */
public class Dot implements Shape{
    /*# YOUR CODE HERE */
    private double x;
    private double y;
    
    //constant
    private double SIZE = 5;
    
    private Color col;

    public Dot (double x, double y, Color col){
        this.x = x;
        this.y = y;
        this.col = col;

    }

    public Dot(String description){
        Scanner data = new Scanner(description);
        int red = data.nextInt();
        int green = data.nextInt();
        int blue = data.nextInt();
        this.col = new Color(red, green, blue);
        this.x = data.nextDouble();
        this.y = data.nextDouble();
        
    }

    public boolean on(double u, double v){
        double radius = this.SIZE/2;
        return (Math.hypot(u-this.x, v-this.y) < radius);
    }

    public void moveBy(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    public void redraw(){
        UI.setColor(this.col);
        UI.fillOval(this.x,this.y,SIZE,SIZE, false);
    }

    public void resize(double changeWd, double changeHt){
        //dot does not resize
    }

    public String toString(){
        return ("Dot "+col.getRed()+" "+col.getGreen()+" "+col.getBlue()+" "+this.x+" "+this.y);
    }

}
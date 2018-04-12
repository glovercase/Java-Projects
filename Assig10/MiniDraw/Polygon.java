// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 10
 * Name:
 * Usercode:
 * ID:
 */

import java.util.*;
import ecs100.*;
import java.awt.Color;
import java.io.*;

/** Polygon represents a polygon made of a sequence of straight lines.
   Implements the Shape interface.
   Has a field to record the colour of the line and two fields to store
   lists of the x coordinates and the y coordinates of all the vertices
 */

public class Polygon implements Shape{
    //fields
    private Color col;
 
    private ArrayList<Double> xPoint = new ArrayList<Double>();
    private ArrayList<Double> yPoint = new ArrayList<Double>();
    // i am not to sure if i was on the right track on getting towards to the polygon working
    // I had to research a bit, but unfortuatly ran out of time, the idea was to have two lists 
    //constructor
    public Polygon(double x, double y, Color col){
        addPointX(x,y);
        this.col = col;
    }
    
    public void addPointX(double x, double y){ 
        xPoint.add(x); 
        yPoint.add(y);
    }
    
    
    public Polygon(String description){
       Scanner data = new Scanner(description);
       int red = data.nextInt();
       int green = data.nextInt();
       int blue = data.nextInt();
       this.col = new Color(red, green, blue);
    }
    
    public boolean on(double x, double y){
        return true;
    }
    
    public void moveBy(double dx , double dy){
        for(int i = 0; i < xPoint.size(); i++){
            xPoint.set(i, xPoint.get(i)+dx); 
            yPoint.set(i, yPoint.get(i)+dy); 
        }
    }
    
    public void redraw(){
        UI.setColor(this.col);
        for(int i = 0; i < xPoint.size(); i++){
            if(i+1 < xPoint.size()){
               UI.drawLine(xPoint.get(i), yPoint.get(i), xPoint.get(i+1), yPoint.get(i+1)); 
            }else{
                UI.drawLine(xPoint.get(i), yPoint.get(i), xPoint.get(0), yPoint.get(0)); 
            }
            
        }
        
    }
    
    public void resize(double changeWd, double changeHt){
        for(int i = 0; i < xPoint.size(); i++){
            xPoint.get(i);
        }
    }
    
    public String toString(){
        return null;
    }
}
  

   

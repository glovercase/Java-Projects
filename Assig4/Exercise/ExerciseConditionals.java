// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for Assignment 4
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** 
 */
public class ExerciseConditionals{

    //    Conditionals:  

    /** Ask user for an integer
        if the number is a valid hour (1 to 12), then it prints the number in
        the form  "The time is 6 o'clock" (if they entered 6
        otherwise it prints  "That number is not a valid time"
    */
    public void validHour(){
        /*# YOUR CODE HERE */
    }

    /** Asks the user to enter a word.
        Says "Yes, that fits" if the word starts with "p" and is 7 characters long,
        and "Sorry, that word won't work" otherwise.
    */
    public void wordGame(){
        /*# YOUR CODE HERE */
    }



    /**  Asks the user to enter the name of a country, and
         draws the appropriate flag (by calling one of the
         methods below).
         Recognises france, indonesia, austria and bangladesh.
    */
    public void drawAFlag(){
        /*# YOUR CODE HERE */
    }

    
    /** Asks the user to enter three words and prints out the longest one.
        (if two words are equally long, it doesn't matter which it prints).
        You can call the length() method on a string to find out how long it is.
        Note that there are three possible cases to check for.
    */
    public void longestWord(){
        /*# YOUR CODE HERE */
    }

    // The flag methods called by drawAFlag.


    /** Draws the French flag */
    public void drawFranceFlag(){
        double width = UI.askDouble("How wide");
        double height = width*2/3;
        UI.clearGraphics();
        UI.setColor(Color.blue);
        UI.fillRect(100,100, width/3, height);
        UI.setColor(Color.red);
        UI.fillRect(100+width*2/3,100, width/3, height);
        UI.setColor(Color.black);
        UI.drawRect(100,100, width, height);
    }
    /** Draws the Indonesian flag */
    public void drawIndonesiaFlag(){
        double width = UI.askDouble("How wide");
        double height = width*2/3;
        UI.clearGraphics();
        UI.setColor(Color.red);
        UI.fillRect(100,100, width, height/2);
        UI.setColor(Color.black);
        UI.drawRect(100,100, width, height);
    }

    /** Draws the Austrian flag */
    public void drawAustriaFlag(){
        double width = UI.askDouble("How wide");
        double height = width*2/3;
        UI.clearGraphics();
        UI.setColor(Color.red);
        UI.fillRect(100,100, width, height/3);
        UI.fillRect(100,100+height*2/3, width, height/3);
        UI.setColor(Color.black);
        UI.drawRect(100,100, width, height);
    }


    /** Draws the Bangladeshi flag  */
    public void drawBangladeshFlag(){
        double width = UI.askDouble("How wide");
        double height = width*3/5;
        double circle = width*2/5;
        UI.clearGraphics();
        UI.setColor(Color.green);
        UI.fillRect(100,100, width, height);
        UI.setColor(Color.red);
        UI.fillOval(100+width/2-circle/2,100+height/2-circle/2, circle, circle);
        UI.setColor(Color.black);
        UI.drawRect(100,100, width, height);
    }


}

// This program is copyright VUW.
// You may not distribute it in any way without permission.

/* Code for COMP 102 Make-up Assignment
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.Scanner;
import java.io.*;


/** Displays an animated cartoon of two characters from a script in a file.
    The first two lines of the file are the names (imagefile names) and
    initial positions of the two characters.
    Each remaining line in the file contains the name of a character,
    followed by a command: lookLeft, lookRight, smile, frown, move, speak
    If the command is "walk", it is followed by a distance to walk
    If the command is "speak", it is followed by the text to say.
 */
public class CartoonAnimator{

    /** animateFromFile opens a script file, and reads the names and intial positions
     *  of the two characters from the first two lines of the file.
     *  It then creates two CartoonFigure objects, using the names as the
     *  imageNames and places them on the window.
     *  It then has a loop to read each instruction from the script file:
     *  Each instruction starts with the name of the character, followed by
     *  a command:  lookLeft, lookRight, smile, frown, walk, or speak
     *  If the command is walk, it must be followed by a single integer
     *  (the distance to walk).
     *  If the command is speak, it must be followed by some words to say.
     *  The method then calls the command on the correct CartoonFigure object.
     *  The loop will exit when there are no more instructions.
     */
    public void animateFromFile(){
        try{
            // open the script file 
            // read the names, image files, and intial positions of the two characters
            // and create the two CartoonFigure objects.
            // Then loop through the instructions
	    /*# YOUR CODE HERE */


        } catch(IOException e){UI.println("File reading failed: "+e);}
    }

    // Main. Please leave this here for the markers to use. 
    public static void main(String[] arguments){
        CartoonAnimator animator = new CartoonAnimator();
        animator.animateFromFile();
    }        


}

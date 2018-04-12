// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 6
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class MiniPaint implements UIButtonListener, UIMouseListener{

    // fields to remember:
    //  the current shape that will be drawn when the mouse is next released.
    //  whether filled or not
    //  the position the mouse was pressed, 
    //  the name of the image file
    /*# YOUR CODE HERE */

    //Constructor
    /** Sets up the user interface - mouselistener and buttons */
    public MiniPaint(){
        /*# YOUR CODE HERE */
    }


    /* Respond to button presses */
    public void buttonPerformed(String cmd){
        /*# YOUR CODE HERE */
    }



    /** Respond to mouse events
        Although you could do all the drawing in this method,
        it may be better to call some helper methods for the more
        complex actions (and then define the methods below!)
     */
    public void mousePerformed(String action, double x, double y) {
        /*# YOUR CODE HERE */
        }
    }

  
    /* Helper methods for drawing the shapes, if you choose to define them */
    /*# YOUR CODE HERE */

  // Main:  constructs new MiniPaint object
  public static void main(String[] arguments){
    MiniPaint ob = new MiniPaint();
  }        


}

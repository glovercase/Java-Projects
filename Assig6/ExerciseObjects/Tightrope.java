// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.


/* Code for Assignment 6
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;


/** A Tightrope object represents an acrobat's tightrope strung between two poles.
    A Tightrope is drawn using two tall thin rectangles representing the two poles
    and a straight line between the poles.
    The poles go from the top of the pane (y = 0) to the ground (y=400).
      They should be 5 units wide.
      (Note, it is fine for this exercise to use thes constants (0, 5, 400) in your code,
       though good practice would define constants for them.)
    The tightrope can be raised, one end at a time.
    The Tightrope object needs to remember
      - the horizontal positions of the two poles (which don't change), and
      - the heights of each end of the "tightrope" line above the
        base (which do change)
    (Therefore, it needs at four fields, there are quite a lot of choices)
    The constructor requires two arguments:
       the horizontal position (mid point between the the two poles), and
       the width of the tightrope.
    Both ends of the tightrope are initially 20 units above the base
      of the poles.
    The constructor will draw the poles, and draw the tightrope in
      its initial position
    |           |
    |           |
    |-----------|
    |           |
    |    mid    |

    The class has two methods:
      raiseLeft(double d),  which changes the height of the left end by the distance d
      raiseRight(double d), which changes the height of the right end by the distance d
    Both methods
      - erase the line from its current position, [UI.eraseLine(....)]
      - change the height of one end,
      - redraw the line   [UI.drawLine(....)]
      They don't need to redraw the poles.
      Note, for this exercise, it doesn't matter if the tightrope goes off
      the top of the poles!
 */
public class Tightrope{

    //fields,
    // x positions of the left and right ends
    // heights of the left end and the right end.
    /*# YOUR CODE HERE */

    /** Constructor:
        Parameters: the horizontal middle of the tightrope and its width
        Initialises the fields,
        draws the two poles, then draws the tightrope.
     */
    /*# YOUR CODE HERE */

    /** raiseLeft method:
        Parameter:  amount to raise the tightrope
        Raises the left end of tightrope by the specified distance
        erases the tightrope, changes the height of the left end, redraws the tightrope.
     */
    /*# YOUR CODE HERE */

    /** raiseRight method:
        Parameter:  amount to raise the tightrope
        Raises the right end of tightrope by the specified distance
        erases the tightrope, changes the height of the right end, redraws the tightrope.
     */
    /*# YOUR CODE HERE */



    //-------------------------------------------------
    /** Run this method from BlueJ to test your class.
        Makes two Tightrope objects and raises the tightropes */
    public static void testTightrope(){
        UI.setColor(new Color(0,100,0)); 
        UI.fillRect(0,400,600,10);  // the ground
        Tightrope training = new Tightrope(110, 100);  // horizontal center and width
        Tightrope perform = new Tightrope(400, 220);   // horizontal center and width
        int steps = 0;
        while (steps < 200){
            // lift a random end of the training tightrope by a random amount
            double amount = Math.random()*10-3;
            if (Math.random() <0.5){
                training.raiseLeft(amount);
            }
            else{
                training.raiseRight(amount);
            }
            // lift a random end of the perform tightrope by a random amount
            amount = Math.random()*15-5;
            if (Math.random() <0.5){
                perform.raiseLeft(amount);
            }
            else{
                perform.raiseRight(amount);
            }

            //pause
            UI.sleep(60);
            steps++;   //(shorthand for steps = steps + 1;
        }
    }

    public static void main(String[] args){
        testTightrope();
    }


}

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


/** A PartyBalloon object represents a small round balloon on the graphics pane.
    It remembers its current position and its color.
    Its initial position and its color are set when it is constructed
    The diameter of a balloon should be 30.
    It has three methods:
      draw(),      which draws the balloon at its current position
      riseLeft(),  which makes the balloon rise up by 20, and move 5 to the left
      riseRight(), which makes the balloon rise up by 20, and move 5 to the right
 */
public class PartyBalloon{

    //fields
    /*# YOUR CODE HERE */

    /** Constructor: passed the intial position and color.
        Initialises the fields
        draws the balloon.
     */
    /*# YOUR CODE HERE */

    /** draw method (no parameters)
        draws the balloon at its current position, of the right colour.
     */
    /*# YOUR CODE HERE */

    /** riseLeft method (no parameters)
        makes balloon move up and to the left
        Does not erase or redraw
     */
    /*# YOUR CODE HERE */

    /** riseRight method (no parameters)
        Makes balloon move up and to the right
        Does not erase or redraw
     */
    /*# YOUR CODE HERE */




    //-------------------------------------------------
    /** Run this method from BlueJ to test your class.
        Makes five pairs of PartyBalloon objects and makes them fly into the sky */
    public static void testPartyBalloon(){
        UI.setColor(Color.black);
        UI.drawLine(0,400, 500, 400);
        int count = 0;
        while (count < 5){
            // make a random strong colour (random Hue, fully Saturated, maximum Brightness)
            Color colr = Color.getHSBColor((float)Math.random(),1,1);
            // choose a random x position
            double x = Math.random()*500;
            // make and draw a new PartyBalloon object.
            PartyBalloon myBalloon = new PartyBalloon(x, 370, colr);
            myBalloon.draw();

            // repeat for a second PartyBalloon.
            colr = Color.getHSBColor((float)Math.random(),1,1);
            x = Math.random()*500;
            PartyBalloon yourBalloon = new PartyBalloon(x, 370, colr);
            yourBalloon.draw();

            // repeatedly move and redraw the balloons.
            int steps = 0;
            while (steps < 18){
                UI.sleep(200);
                UI.eraseRect(0,0,700,400);
                //move the balloons
                myBalloon.riseLeft();
                yourBalloon.riseRight();
                //redraw the balloons
                myBalloon.draw();
                yourBalloon.draw();
                steps = steps + 1;
            }
            count = count + 1;
        }
    }

    public static void main(String[] args){
        testPartyBalloon();
    }

}

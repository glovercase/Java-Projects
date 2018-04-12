// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.


/* Code for Assignment 6 COMP102
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;


/** A CameraAperture object represents the aperture of a camera, which can be opened or closed.
    It is drawn using a cicle outline of the camera lens, and a solid black circle
      representing the aperture. The aperture may be varied (in small steps) between wide open,
      (about 80% of the camera lens) and closed (about 20% of the camera lens)
    The CameraAperture object needs to remember
      - the position of the lens (which doesn't change), and
      the width of the aperture (which does change)
    The constructor requires two arguments:
       the x and y position of the lens
    The aperture is initially wide open

    The class has two methods:
      open(),  which opens the aperture by a further 10% (unless it is fully open)
      close(), which closes the aperture by a further 10% (unless it is fully closed)
      They don't need to redraw the lens outline
 */
public class CameraAperture{

    //fields
    static final double lensWidth = 100;   // width of the whole lens
    
    // fields for the position of the center of the lens and for the width of the aperture.
    /*# YOUR CODE HERE */

    //Constructor: passed the center of the lens. Draws the camera
    // (rectangle for camera, circle for lens, black circle for aperature.
    /*# YOUR CODE HERE */

    /** open method (no parameters)
        If current width less than 80% of lensWidth, increase it by 10% of lensWidth
        redraws the aperture
    */
    /*# YOUR CODE HERE */

    /** close method (no parameters)
        if current width greather than 20% of lensWidth, decrease it by 10% of lensWidth
        redraws the aperture
    */
    /*# YOUR CODE HERE */



    //-------------------------------------------------
    /** Run this method from bluej to test your program.
        Makes two Tightrope objects and raises the tightropes */
    public static void main(String[] args){
        CameraAperture cam1 = new CameraAperture(150, 300); 
        CameraAperture cam2 = new CameraAperture(400, 200);
        UI.setColor(Color.black);
        int steps = 0;
        while (steps < 50){
            // at random, choose camera and make it open or close
            if (Math.random() <0.5){
                if (Math.random() <0.5){
                    cam1.open();
                }
                else {
                    cam1.close();
                }
            }
            else {
                if (Math.random() <0.5){
                    cam2.open();
                }
                else {
                    cam2.close();
                }
            }
            //pause
            UI.sleep(200);
            steps++;   //(shorthand for steps = steps + 1;
        }
    }



}

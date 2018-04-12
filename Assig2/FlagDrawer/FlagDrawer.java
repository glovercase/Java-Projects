// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for Assignment 2  COMP102
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/**
 * Draws flags of various countries.
 * The correct dimensions of the official flags varies from country to country,
 * But you may assume that they are 2/3 as high as they are wide.
 * The exact colours of the flags will also be difficult to match;
 * It is fine to use the standard colours: red, green, blue, orange, etc
 * You can find lots of flag details (including the correct dimensions and colours)
 * from   http://www.crwflags.com/fotw/flags/    
 */
public class FlagDrawer{

    public static final double top = 100;
    public static final double left = 200;
    

    /**
     * The flag for Denmark is a red rectangle with
     * a white cross over it.
     * (alternatively, it is four red rectangles on a white background!)
     */
    public void denmarkFlag() {
        UI.initialise();      // not necessary, but avoids some problems when debugging
        /*# YOUR CODE HERE */
        double width = UI.askDouble("how wide?");
        double height = width * 0.66;
        double stripe = height/6;
       
        UI.setColor(Color.red);
        UI.fillRect(left, top, width, height);
        UI.setColor(Color.white);
        UI.fillRect(left, top + height/2 - stripe/2, width, stripe);
        UI.fillRect(left + width/3, top, stripe, height);
        UI.setColor(Color.black);
        UI.drawRect(left, top, width, height);

    }
    
    
    /**
     * The flag for Greece has blue and white strips, and
     * a white cross on a blue background in the top left corner.
     * The blue is a dark blue. 
     */
    public void greeceFlag() {
        UI.initialise();      // not necessary, but avoids some problems when debugging
        // involves drawing quite a lot of rectangles!
        /*# YOUR CODE HERE */
        double width = UI.askDouble("how wide?");
        double height = width * 0.66;
        double stripe = height/9;
       
        UI.setColor(Color.blue.darker());
        UI.fillRect(left, top, width, height);
        UI.setColor(Color.white);
        UI.fillRect(left, top + height * 7/9, width, stripe);
        UI.fillRect(left, top+height * 5/9, width, stripe);
        UI.fillRect(left + (width*1/3), top+height* 3/9, width * 2/3, stripe);
        UI.fillRect(left + (width*1/3), top+height* 1/9, width * 2/3, stripe);
        UI.fillRect(left, top+height* 2/9, width *1/3, stripe);
        UI.fillRect(left + (width*1/9), top, stripe, top+height * 1/9);
        UI.setColor(Color.black);
        UI.drawRect(left, top, width, height);

    }

    /**
     * The Mauritania flag (simplified)  is a green background with
     * a yellow crescent moon and a yellow circle.
     * (The real flag has a 5 pointed star in place of the circle
     */
    public void simplifiedMauritaniaFlag() {
        /*# YOUR CODE HERE */
        UI.initialise();
        double width = UI.askDouble("?");
        double height = width * 0.66;
        double oval = width/3;
        
        UI.setColor(Color.green.darker());
        UI.fillRect(left, top, width, height);
        UI.setColor(Color.yellow);
        UI.fillOval(left+width/2-oval/2, top+height*1/2-oval/2, oval, oval);
        UI.setColor(Color.green.darker());
        
        UI.fillOval(left+width/2-oval/2, top+height*1/2-oval*6/9, oval, oval);
        UI.setColor(Color.yellow);
        UI.fillOval(left+width/2-oval*2/9, top+height*2/3-oval*6/9, oval/2, oval/2);
        
    }

    /**
     * Completion: Six pointed Star on a white background.
     * The lines should be several pixels thick.
     */
    public void starFlag() {
        /*# YOUR CODE HERE */
    }

    /**
     * Completion: pacman
     * A yellow pacman on a black background chasing white, red, and green dots.
     */
    public void pacmanFlag() {
        /*# YOUR CODE HERE */

    }
    /**
     * Challenge: The flag of Israel
     * Two horizontal blue stripes and a 6 pointed star on a white background
     */
    public void israelFlag() {
        /*# YOUR CODE HERE */
    }




    /** 
     *  Challenge: the flag of the Seychelles
     *  Radiating triangles.
     */
    public void seychellesFlag() {
        /*# YOUR CODE HERE */
        UI.initialise();
        double width = UI.askDouble("?");
        double height = width * 0.5;
        
        UI.setColor(Color.blue.darker());
        UI.fillRect(left, top, width, height);
        UI.setColor(Color.yellow);
        double xPoly[] = {left, left+width*1/3, left+width*2/3};
        double yPoly[] = {top+height, top, top};
        UI.fillPolygon(xPoly, yPoly, xPoly.length);
        UI.setColor(Color.red);
        double rxp[] = {left, left+width*2/3, left+width, left+width };
        double ryp[] = {top+height,top, top, top+height*1/3 };
        UI.fillPolygon(rxp, ryp, rxp.length);
        UI.setColor(Color.white);
        double wxp[] = {left, left+width, left+width};
        double wyp[] = {top+height, top+height*1/3, top+height*2/3};
        UI.fillPolygon(wxp, wyp, wxp.length);
        UI.setColor(Color.green.darker());
        double gxp[] = {left, left+width, left+width};
        double gyp[] = {top+height, top+height*2/3, top+height};
        UI.fillPolygon(gxp,gyp, gxp.length);
        //UI.fillRect(left,top,width,height);
        UI.setColor(Color.red);
        //UI.fillRect();
        UI.setColor(Color.white);
        //UI.fillRect();
        
    }


}

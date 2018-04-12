// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for Assignment 7
 * Name: Casey Glover
 * Usercode: I worked with chloe graham
 * ID: 300280613
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 *  Lets a player play a simple Solitaire dominoes game.
 *  Dominoes are rectangular tiles with two numbers from 0 to 6 on
 *  them (shown with dots).
 *  The player has a "hand" which can contain up to six dominoes.
 *  They can reorder the dominoes in their hand, they can place dominoes
 *  from their hand onto the table, and they can pick up more dominoes from a bag
 *  to fill the gaps in their "hand".
 *  The core and completion do not involve any of the matching and scoring
 *  of real dominoes games. 
 *
 *  PROGRAM DESIGN
 *  The dominoes are represented by objects of the Domino class.
 *  The Domino constructor will construct a new, randomo domino.
 *  Dominos have a draw(double x, double y) method that will draw the
 *  Domino on the graphics pane at the specified position.
 *  
 *  The program has two key fields:
 *    hand:  an array of up to 6 Dominos. Uses an array with nulls.
 *    table: an arrayList of the Dominos that have been placed on the table.
 *    
 *
 *  The hand should be displayed near the top of the Graphics pane with a
 *   rectangular border and each domino drawn at its place in the hand.
 *  Empty spaces in the hand should be represented by nulls and displayed as empty.
 *
 *  The user can select a position on the hand using the mouse.
 *  The selected domino (or empty space) should be highlighted with
 *  a border around it.
 *  
 *  The user can use the "Left" or "Right" button to move the selected domino
 *  (or the space) to the left or the right, in which case the domino is
 *  swapped with the contents of the adjacent position in the hand.
 *  If the selected position contains a domino, the user
 *  can use the "Place" button to move the selected domino to the table.
 *  
 *  If there are any empty positions on the hand, the user can use the
 *  "Pickup" button to get a new (random) domino which will be added to
 *  the hand at the leftmost empty position.
 *
 *  The table is represented by an ArrayList of dominos.
 *  At the beginning of the game the table should be empty.
 *  Dominos should be added to the end of the table.
 *  The table should be displayed in rows at the top of the graphics pane.
 */

public class DominoGame implements UIMouseListener, UIButtonListener{
    // Fields
    private Domino[] hand;            // the hand (fixed size array of Dominos)
    private ArrayList<Domino> table;  // the table (variable sized list of Dominos)

    private int selectedPos = 0;      //  selected position in the hand.

    // (You shouldn't add any more fields for core or completion)

    // constants for the layout
    private static final int HAND_LEFT = 60; // Position to draw the Hand
    private static final int HAND_TOP = 5;   
    private static final int HAND_HEIGHT = Domino.HEIGHT+8;
    private static final int DOMINO_SPACING = Domino.WIDTH+4;

    //spacing is the distance from left side of domino to left side of next domino

    private static final int TABLE_LEFT = 10;                
    private static final int TABLE_TOP = HAND_TOP + HAND_HEIGHT + 10;   

    /**  Constructor:
     * Initialise the hand and table fields,
     * set up the GUI (buttons and mouse listener)
     *  restart the game
     */
    public DominoGame(){
        /*# YOUR CODE HERE */
        hand = new Domino[] {null, null, null, null, null, null}; // array of domino hand
        table = new ArrayList<Domino>(); // table array list
        //GUI buttons
        UI.addButton("Pickup", this);
        UI.addButton("Place", this);
        UI.addButton("Flip", this);
        UI.addButton("Left", this);
        UI.addButton("Right", this);
        UI.addButton("Restart", this);
        UI.addButton("Quit", this);
        UI.setMouseListener(this);

        this.restart(); //restart method

        this.redraw(); //redraw method
    }
    //challenge
    public DominoBag(){
        hand = new Domino[] {null, null, null, null, null, null}; // array of domino hand
        table = new ArrayList<Domino>(); // table array list
        //GUI buttons
        UI.addButton("Pickup", this);
        UI.addButton("Place", this);
        UI.addButton("Flip", this);
        UI.addButton("Left", this);
        UI.addButton("Right", this);
        UI.addButton("Restart", this);
        UI.addButton("Quit", this);
        UI.setMouseListener(this);

        this.restart(); //restart method

        this.redraw(); //redraw method

    }

    public void pickupDominoBag(){ //started the challenge idea is to loop through twice collecting each type of domino
        /*# YOUR CODE HERE */
        for(int i = 0; i < 6; i++){ //for loop for the hand
            for(int i = 0; i < 6; i++){
                if(hand[i] == null){ //if hand is not equal to null
                    hand[i] = new Domino(); //make a new domino
                    break; //then break out of loop
                }
            }
        }

    }

    /** If the mouse is released over the hand, then set the selected position
    and redraw */
    public void mousePerformed(String action, double x, double y){
        if (action.equals("released")){
            if (y >= HAND_TOP && 
            y <= HAND_TOP + HAND_HEIGHT && 
            x >= HAND_LEFT &&
            x <= HAND_LEFT+this.hand.length*DOMINO_SPACING) {
                this.selectedPos = (int) ((x-HAND_LEFT)/DOMINO_SPACING);
                UI.clearText();UI.println("selected "+this.selectedPos);
                this.redraw();
            }
        }
    }

    /**
     * Respond to the buttons by calling methods.
     * (Must match names of buttons in constructor)
     */
    public void buttonPerformed(String button){
        if (button.equals("Pickup"))     { this.pickupDomino(); }
        else if (button.equals("Place")) { this.placeDomino(); }
        else if (button.equals("Flip"))  { this.flipDomino(); }
        else if (button.equals("Left"))  { this.moveLeft(); }
        else if (button.equals("Right")) { this.moveRight(); }
        else if (button.equals("Restart")) { this.restart(); }
        else if (button.equals("Quit"))  { UI.quit(); }
        this.redraw();
    }

    /**
     * Restart the game:
     *  set the table to be empty,
     *  set the hand to have no dominos
     */
    public void restart(){
        /*# YOUR CODE HERE */
        hand = new Domino[] {null, null, null, null, null, null}; //resetting the hand
        table.clear(); //resetting the table

    }

    /**
     * If there is at least one empty position on the hand, then
     * create a new random domino and put it into the first empty position on the hand.
     */
    public void pickupDomino(){
        /*# YOUR CODE HERE */
        for(int i = 0; i < 6; i++){ //for loop for the hand
            if(hand[i] == null){ //if hand is not equal to null
                hand[i] = new Domino(); //make a new domino
                break; //then break out of loop
            }
        }

    }

    /**
     *  Redraw the table and the hand.
     *  To work with the code above, this needs to use the constants:
     *   - DOMINO_SPACING, HAND_HEIGHT, HAND_LEFT, HAND_TOP, TABLE_LEFT, TABLE_TOP
     *   See the descriptions where these fields are defined.
     *  Needs to clear the graphics pane,
     *  then draw the hand with all its dominos, 
     *  then outline the selected position on the hand
     *  then draw the rows of dominos on the table.
     */
    public void redraw(){
        /*# YOUR CODE HERE */
        UI.clearGraphics(); //clear graphics
        UI.repaintGraphics(); 
        UI.setImmediateRepaint(false); // speeds up graphics to repaint
        UI.setColor(Color.black); // hand set to black
        UI.drawRect(HAND_LEFT, HAND_TOP, DOMINO_SPACING*6, HAND_HEIGHT); // hand rect

        for(int i = 0; i < 6; i++){
            if(hand[i] != null){
                hand[i].draw(HAND_LEFT+DOMINO_SPACING*i, HAND_TOP); //draws new domino
            }
        }
        UI.setColor(Color.green); // selected color is green
        UI.drawRect(HAND_LEFT+DOMINO_SPACING*selectedPos, HAND_TOP,Domino.WIDTH+2,Domino.HEIGHT+4);
        //selected rect
        //I started the challenge here but it didn't like me calling get.Top??
        //if(hand[selectedPos].getTop() = table.size() || hand[selectedPos].getBottom() = table.size()){
        for(int i = 0; i < table.size(); i++){
            int row = i / 10; // sets the row
            int column = i % 10; // sets the column
            table.get(i).draw(TABLE_LEFT+DOMINO_SPACING*column, TABLE_TOP+row*Domino.HEIGHT); 
        } //draws a domino on the table
        //}

    }

    /**
     * Move domino from selected position on hand (if there is domino there) to the table
     */
    public void placeDomino(){
        /*# YOUR CODE HERE */
        if(hand[selectedPos] != null){ 
            table.add(hand[selectedPos]); //add selected domino to table
            hand[selectedPos] = null; //sets selected pos to null
        }

    }

    /**
     * If there is a domino at the selected position in the hand, 
     * flip it over.
     */
    public void flipDomino(){
        /*# YOUR CODE HERE */
        if(hand[selectedPos] != null){ 
            hand[selectedPos].flip(); //flips selected domino
        }

    }

    /**
     * Swap the contents of the selected position on hand with the
     * position on its left (if there is such a position)
     * and also decrement the selected position to follow the domino 
     */
    public void moveLeft(){
        /*# YOUR CODE HERE */
        if(selectedPos > 0){
            Domino temp = hand[selectedPos]; // puts selected domino in a temp array
            hand[selectedPos] = hand[selectedPos -1]; //moves the left domino to the selected pos
            hand[selectedPos-1] = temp; // puts left domino in the temp array
        }

    }

    /**
     * Swap the contents of the selected position on hand with the
     *  position on its right (if there is such a position)
     *  and also increment the selected position to follow the domino 
     */
    public void moveRight(){
        /*# YOUR CODE HERE */
        if(selectedPos < 5){
            Domino temp = hand[selectedPos]; // same as above but to the right
            hand[selectedPos] = hand[selectedPos +1];
            hand[selectedPos+1] = temp;
        }

    }

    public static void main(String[] args){
        DominoGame obj = new DominoGame();
    }   

}

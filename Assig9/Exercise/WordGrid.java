// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 9
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** WordGrid
 */
public class WordGrid implements UIButtonListener, UITextFieldListener, UIMouseListener{
    // Fields
    private int selectedRow = 0;  // selected cell
    private int selectedCol = 0;

    private String currentWord = "-";

    private int left =50;  // position of left top corner of table
    private int top = 20;
    private int wd = 60;   // size of cells
    private int ht = 20;

    private String[][] wordGrid = new String[10][8];

    /** Construct a new WordGrid object and set up the GUI
     */
    public WordGrid(){
        UI.setMouseListener(this);
        UI.addTextField("Word", this);
        UI.addButton("Set Entry", this);
        UI.addButton("Delete Entry", this);
        UI.addButton("Load Words", this);
        UI.addButton("Save Words", this);
        this.redisplay();
    }

    /** Display the grid of words.
     *  Draw a rectangle for every element, 
     *   but only draw the string if it is not null
     *  Redrawing will be smoother if you use the optional false argument for
     *   the drawing commands
     */
    public void redisplay(){
        UI.clearGraphics(false);
        UI.setColor(Color.black);
        /*# YOUR CODE HERE */
        double y = top;
        for(int row = 0; row < wordGrid.length; row++){
            double x = left;
            for( int col = 0; col < wordGrid.length; col++){
                UI.drawRect(x,y, wd, ht);
                x+= wd;
                if(currentWord != null){
                   // UI.drawString(wordGrid[row][col], row, col, true);
                }

            }
            y+= ht;

        }
        //highlights the selected cell
        UI.setColor(Color.red);
        UI.drawRect(this.left+this.selectedCol*this.wd,
            this.top+this.selectedRow*this.ht,
            this.wd, this.ht, false);
        UI.repaintGraphics();
    }


    /* Load file into the array of words
     *  File represented as a sparse array:
     *   first line has size (number of rows and cols)
     *   other lines have row, col, entry for each non-null element
     *  Construct array of correct size, then read and add every entry.
     */
    public void loadSparse(){
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            /*# YOUR CODE HERE */

            sc.close();
        }
        catch(IOException e){UI.println("Fail: " + e);}
    }

    /** Saves the grid as a sparse array.
     *   first line should have size (number of rows and cols)
     *   other lines have row, col, entry for each non-null element
     */
    public void saveSparse(){
        String fname = UIFileChooser.save();
        if (fname==null) return;  // user didn't select a file.
        try{
            PrintStream out = new PrintStream(new File(fname));
            /*# YOUR CODE HERE */

            out.close();
        }
        catch(IOException e){UI.println("Fail: " + e);}
    }


    // GUI Methods

    /** Respond to button presses */
    public void buttonPerformed(String button){
        if (button.equals("Set Entry") ){
            this.wordGrid[this.selectedRow][this.selectedCol] = this.currentWord;
            this.redisplay();
        }
        else if (button.equals("Delete Entry") ){
            this.wordGrid[this.selectedRow][this.selectedCol] = null;
            this.redisplay();
        }
        else if (button.equals("Load Words") ){
            this.loadSparse();
            this.redisplay();
        }
        else if (button.equals("Save Words") ){
            this.saveSparse();
            UI.println("Saved");
        }
    }

    /** Respond to textField entries */
    public void textFieldPerformed(String field, String value){
        if (field.equals("Word") ){
            this.currentWord = value;
            this.redisplay();
        }
    }

    /** Respond to mouse events */
    public void mousePerformed(String action, double x, double y) {
        if (action.equals("released")){
            int c = ((int)x - this.left)/ this.wd;
            int r = ((int)y - this.top)/ this.ht;
            if (r >= 0 && r < this.wordGrid.length && c >=0 && c < this.wordGrid[0].length){
                this.selectedRow = r;
                this.selectedCol = c;
                this.redisplay();
            }
        }
    }


    // Main
    public static void main(String[] arguments){
        new WordGrid ();
    }        

}

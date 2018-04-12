// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

// look at http://www.wordfinds.net/

public class RayBitsSquare implements UIButtonListener
{
    private final int size = 5;
    private Color[][] puzzle = new Color[size][size];
    private double sqSize = 30;

    // layout of the grid
    private final int gridLeft = 60;    // left edge of the grid
    private final int gridTop = 30;     // top edge of the grid
    private final int cellWidth = 30;   // width of cells in the grid
    private final int cellHeight = 30;  // height of cells in the grid

    /** creates a cross-word of x columns and y rows */
    public RayBitsSquare(){
        UI.addButton("New", this);
        UI.addButton("Check Cols", this);
        UI.addButton("Shift Left", this);
        this.newPuzzle();
    }

    /** Respond to button presses */
    public void buttonPerformed(String button){
        if (button.equals("New") ){this.newPuzzle();}
        else if (button.equals("Check Cols") ){this.reportCols();}
        else if (button.equals("Shift Left") ){this.shiftLeft();}
    }


    /** Makes a new puzzle with random colours selected from red, green, and blue */
    public void newPuzzle(){
        Color[] colors = new Color[]{Color.red, Color.green, Color.blue};
        for (int row=0; row<this.size; row++){
            for (int col=0; col<this.size; col++){
                int randomIndex = (int)(Math.random()*colors.length);
                this.puzzle[row][col] = colors[randomIndex];
            }
        }
        this.drawPuzzle();
    }

    /** Draws the puzzle */
    private void drawPuzzle(){
        UI.clearGraphics();
        /*# YOUR CODE HERE */
        UI.setImmediateRepaint(false);
        double y = gridTop;
        for( int row=0; row < puzzle.length; row++){
            double x = gridLeft;
            for(int col = 0; col < puzzle.length; col++){
                UI.setColor(puzzle[row][col]);
                UI.fillRect(x,y, cellWidth, cellHeight);
                UI.setColor(Color.black);
                UI.drawRect(x,y, cellWidth, cellHeight);
                x+=cellWidth;
            }
            y+=cellHeight;
        }

        UI.repaintGraphics();
    }

    /** Shifts every value one step to the left, except for cells in the
     *  leftmost column, which are moved to the right most column */
    public void shiftLeft() {
        /*# YOUR CODE HERE */

        for(int row =0; row < puzzle.length; row++){
            Color temp = puzzle[row][0];

            for(int col = 0; col< puzzle.length-1; col++){
                puzzle[row][col] = puzzle[row][col+1];
                
            }
            puzzle[row][size-1] = temp;

        }
        //puzzle[size] = temppuzzle;

        this.drawPuzzle();
    }

    /** Checks whether all the colours in the specified column are the same
     *  Returns true if they are, and false otherwise. */
    public boolean checkCol(int col){
        /*# YOUR CODE HERE */

        for(int row = 0; row<size-1; row++){
            if(puzzle[row][col] != puzzle[row+1][col]){
                return false;
            }

        }
        return true;
    }

    /** Calls the checkCol method on each column */
    public void reportCols(){
        UI.clearText();
        for (int col=0; col<this.size; col++){
            UI.print("Col "+col+": ");
            if (this.checkCol(col)) {
                UI.println("Colors are all the same");
            }
            else {
                UI.println("not same");
            }
        }
    }

    public static void main(String[] args){
        RayBitsSquare obj = new RayBitsSquare();
    }    

}

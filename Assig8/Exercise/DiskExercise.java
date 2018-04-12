// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 8
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** Creates two Disk objects at positions (200,100)  and (210,130), and
stores them in an array (of size 2).
Whenever the user clicks on a disk (which isn't broken), then the disk gets damaged..
When a disk is damaged so that it is now broken, it should be made to explode
If the disk is within range of the other disk, the other disk should be damaged also
It then redraws both disks
 */

public class DiskExercise implements UIButtonListener, UIMouseListener{

    Disk[] disks = new Disk[2];

    /** 
     *  Make the DiskExercise object listen to the mouse
     *  and add a "New Disks" button
     */
    public DiskExercise(){
        UI.setMouseListener(this);
        UI.addButton("New Disks", this);
    }

    /**
     * Clear graphics pane,
     * create two new Disks in the disks array,
     * draw the disks.
     */
    public void restart(){
        UI.clearGraphics();
        /*# YOUR CODE HERE */
        int x = 200;
        int y = 100;
        for(int i = 0; i < disks.length; i++){
            disks[i] = new Disk(x,y);
            disks[i].draw();
            x= 210;
            y = 130;
        }

    }

    /**
     * When  the user releases the mouse
     *   Find out if it is on a disk,
     *     if so, damage the disk
     *       if the disk is now broken
     *         explode it,
     *         if the other disk is within range
     *           damage it also.
     *   Clear the graphics and redraw both disks,
     *   This can be inside the mousePerformed method, or it could call another method.
     *   [note, if the current disk is disks[i], then the "other" disk is disks[1-i]    ]
     */
    public void mousePerformed(String action, double x, double y){
        if (action.equals("released")){
            /*# YOUR CODE HERE */
            for(Disk d: disks){
                if(d.on(x,y) == true){
                    d.damage();
                    if(d.isBroken() == true){
                        d.explode();
                        for(Disk disk: disks){
                            if(d.withinRange(disk) == true){
                                

                                    disk.damage();
                                    disk.draw();
                                
                            }
                        }

                    }
                }
                d.draw();
            }
        }
    }

    public void buttonPerformed(String button){
        if (button.equals("New Disks")){
            this.restart();
        }
    }

    public static void main(String[] args){
        new DiskExercise();
    }

}

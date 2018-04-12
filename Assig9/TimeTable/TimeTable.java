// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 102 Assignment 9
 * Name: Casey Glover
 * Usercode: I worked with Chloe Graham
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** This TimeTable program will allow the user to record and modify events
 *  for a weekly timetable
 *  The program will first display an empty grid of events.
 *  The user can then:
 *  - create a new timetable of events 
 *  - load a week of events from a file,
 *  - save events to a file,
 *  - use the mouse to select a cell (day/hour) 
 *  - add an event to the selected cell (if not currently full)
 *  - delete the event in the selected cell
 *  - ask the program to find a sequence of hours on the same day that are free.
 *
 * The timetable is represented by a (2D) array of Event objects, indexed
 *   by the day (0 .. DAYS-1) and the hour (MIN_HOUR .. MAX_HOUR) 
 *
 * A slot that has no event is represented by a null value in
 *   the corresponding element of the array.
 */

public class TimeTable implements UIButtonListener, UIMouseListener, UITextFieldListener{

    // Times:  the hour, between 8 and 17 (5pm)
    private static final int MIN_HOUR = 8;   // the earliest hour in the timetable
    private static final int MAX_HOUR = 17;  // the latest hour in the timetable 17

    private static final int DAYS = 7;
    private Event[][] events = new Event[DAYS][MAX_HOUR + 1];

    // Fields for the interface
    private int selectedHour = MIN_HOUR;
    private int selectedDay = 0;
    private String currentDesc;
    private String currentLoc;

    // layout of the grid
    private final int gridLeft = 80;    // left edge of the grid
    private final int gridTop = 40;     // top edge of the grid
    private final int cellWidth = 90;   // width of cells in the grid
    private final int cellHeight = 35;  // height of cells in the grid

    /** Construct a new TimeTable object
     *  set up the GUI (mouse, buttons)
     *  displays the timetable grid
     */
    public TimeTable(){
        UI.setMouseListener(this);
        UI.addButton("New", this);
        UI.addButton("Load", this);
        UI.addButton("Save", this);
        UI.addTextField("Description", this);
        UI.addTextField("Location", this);
        UI.addButton("Add", this);
        UI.addButton("Delete", this);
        UI.addButton("Search", this);
        
        UI.addButton("Quit", this);

        this.redisplay();
    }

    /**
     * Respond to button presses
     */
    public void buttonPerformed(String button){
        if (button.equals("New") ){
            this.events = new Event[DAYS][MAX_HOUR + 1];
            this.redisplay();
        }
        else if (button.equals("Load"))   { this.doLoad(); }
        else if (button.equals("Save"))   { this.doSave(); }
        else if (button.equals("Add"))    { this.doAdd(); }
        else if (button.equals("Delete")) { this.doDelete(); }
        else if (button.equals("Search")) { this.doSearch(); }
        else if (button.equals("Quit"))   { UI.quit(); }
    }

    /*
     * Store the values in a textField into the appropriate field of the object
     */
    public void textFieldPerformed(String field, String value){
        if (field.equals("Description")){
            this.currentDesc = value;
        }
        else if (field.equals("Location")){
            this.currentLoc = value;
        }
    }

    /**
     * If the user clicks on a cell, it makes this cell be the selected cell:
     * Works out the day from the x position
     * Works out the hour from the y position
     * Checks that the day and hour are within bounds
     * Stores the day and hour in the appropriate fields
     * Redisplays the timetable
     */

    public void mousePerformed(String action, double x, double y) {
        if (action.equals("released")){
            int day =  (int)((x-this.gridLeft) / this.cellWidth);
            int hour = MIN_HOUR + (int)((y-this.gridTop) / this.cellHeight);
            if (day>=0 && day <DAYS && hour>=MIN_HOUR && hour <= MAX_HOUR){ 
                this.selectedHour = hour;
                this.selectedDay = day;
                this.redisplay();
            }
        }
    }

    /**
     * Display the Event chart on the graphics pane
     *  Displays the days and hours in the margins
     *  Displays all the events
     *  Hilights the current day and hour
     *  (by displaying it with a different background colour)
     * NOTE: should use the displayCell method to draw each cell.
     */
    private void redisplay(){
        // display all the cells with the activities
        // redisplay (and highlight) the current cell
        UI.clearGraphics();
        this.displayTitles();
        /*# YOUR CODE HERE */
        UI.setColor(Color.black);
        double y = gridTop;
        double x = gridLeft;
        for(int row = 0; row < events.length; row++){        
            for( int col = MIN_HOUR; col < events[0].length; col++){
                displayCell(row,col, Color.white ); 
            }
        }

        displayCell(selectedDay,selectedHour, Color.blue);

        UI.repaintGraphics();
    }

    /** [Already completed for you]
     * Displays one cell (rectangular region of the grid) on the
     * graphics pane, along with any Event in it.
     * The first two arguments specify the day and the hour 
     * The third argument is the colour of the background. 
     */
    private void displayCell(int day, int hour, Color c){
        int x = this.gridLeft + day*this.cellWidth;   // left of cell
        int y = this.gridTop + (hour-MIN_HOUR)*this.cellHeight;  // top of cell
        UI.setColor(c);
        UI.fillRect(x, y, this.cellWidth, this.cellHeight, false);
        UI.setColor(Color.black);
        UI.drawRect(x, y, this.cellWidth, this.cellHeight, false);   // outline
        if ( this.events[day][hour] != null){
            UI.drawString(this.events[day][hour].getDesc(), x+2, y+this.cellHeight/2, false);
            UI.drawString(this.events[day][hour].getLoc(), x+2, y+this.cellHeight-2, false);
        }
    }

    /** Delete the event in the currently selected cell, then redisplay */
    public void doDelete() {
        /*# YOUR CODE HERE */
        events[selectedDay][selectedHour] = null; //deletes event
        redisplay();

    }

    /**
     * Add an event in the selected cell, unless
     * - The cell already has an event or
     * - The user has not entered a description or location in the text fields
     *   in which case, it should display an appropriate message in the text pane
     * Redisplays the timetable.
     */
    public void doAdd() {
        /*# YOUR CODE HERE */
        if(currentDesc.equals("")){
            UI.println("Enter a Description");
        } else if(currentLoc.equals("")){
            UI.println("Enter a Location"); 
        }          
        else{
            events[selectedDay][selectedHour] = new Event(currentDesc,currentLoc); //new event where selected
            redisplay();
        }

    }

    /** Load a TimeTable from a file and redisplay.
     * Saved files are of the form:
     *          day hour
     *          description
     *          location
     *          day hour
     *          description
     *          location
     *          .....
     *  Asks the user to select a file.
     *  For each entry, 
     *  It should read the day and the hour as integers, and then the event details.
     *  Be careful:  after reading the day and hour, the scanner will be pointing at the end of the
     *  line, not at the beginning of the line with the name!
     */
    public void doLoad() {
        /*# YOUR CODE HERE */
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            /*# YOUR CODE HERE */

            while(sc.hasNext()){
                int day = sc.nextInt();
                int hour = sc.nextInt();
                sc.nextLine();
                String description = sc.nextLine(); 
                String location = sc.nextLine();
                events[day][hour] = new Event(description,location); //extrecting content from file putting into array

            }

            sc.close();
            redisplay();
        }
        catch(IOException e){UI.println("Fail: " + e);}

    }

    /**
     * Save a TimeTable to a file in a form that can be loaded again later.
     * (ie, the same form as used in doLoad above).
     * Asks the user to choose a file.
     */
    public void doSave() {
        /*# YOUR CODE HERE */
        String fname = UIFileChooser.save();
        if(fname==null) return; //user didn't select a file
        try{
            PrintStream out = new PrintStream(new File(fname)); //new print stream new file called fname
            for(int row = 0; row<events.length; row++){
                for(int col = 0; col<events[0].length; col++){
                    if(events[row][col] != null){ //put the content onto a text file
                        out.println(row+" "+col+"\n"+events[row][col].getDesc()+"\n"+events[row][col].getLoc());
                    }
                }
            }
          
            out.close(); //close the file

        }
        catch(IOException e){UI.println("Fail: " + e);}

    }

    /** [COMPLETION ONLY]
     * Ask for the number of hours required, and then
     *  search for a day that has a block of that many hours free.
     *  Highlight the cells in red. (Don't forget to repaint the graphics)
     */
    public void doSearch() {
        this.redisplay();
        int hoursWanted = UI.askInt("Number of hours required"); 
        int hoursFree = 0;
        for(int day = 0; day<events.length; day++){ //loop throug days
            for(int hour = MIN_HOUR; hour<MAX_HOUR+1; hour++){ //loop through hours
                if(events[day][hour] == null){  
                    hoursFree++;//if there is a free time slot add 1
                }
                if( events[day][hour] != null){
                    hoursFree = 0; //if there isn't a free slot set back to 0 but continue
                    continue; 
                }
                if(hoursFree == hoursWanted){ //time slot equals time required 
                     for(int i = hoursFree; i>0; i--){ 
                        displayCell(day,hour-i+1, Color.red);   //displays time wanted
                    }
                    UI.repaintGraphics();
                    return; //finish
                }
            }
            hoursFree = 0; //set back to zero for the next day
        }
    }
    
    // ==========================================================
    //                   HELPER METHOD
    //        These are already completed for you.
    // ==========================================================

    private String[] dayNames = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    /**
     * Display days and hours on the margins of the grid
     */
    private void displayTitles(){
        // display the titles on the margins
        for (int day = 0; day < DAYS; day++) {
            int x = this.gridLeft + day*this.cellWidth;
            UI.drawString(this.dayNames[day], x+10, this.gridTop-5, false);
        }
        for (int hour=MIN_HOUR; hour<=MAX_HOUR; hour++) {
            int y = this.gridTop + (hour-MIN_HOUR)*this.cellHeight;
            UI.drawString(hour+":00-"+(hour+1)+":00", this.gridLeft-80, y+this.cellHeight*2/3, false);
        }
    }

    // Main
    public static void main(String[] arguments){
        new TimeTable();
    }   

}

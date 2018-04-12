// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 Assignment 10
 * Name: Casey Glover
 * Usercode:
 * ID:300280613
 */

import ecs100.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JColorChooser;

/** The MiniDraw program allows the user to create, save, and reload files
specifying drawings consisting of a list of simple shapes.
The program allows the user to
- add a new shape to the drawing
- remove a shape from the drawing
- move a shape to a different position
- set the colour for the next shape
- save the current drawing to a file
- load a previous drawing from a file.
The shapes include lines, rectangles, ovals, and dots

Classes
The MiniDraw class handles all the user interaction:
buttons, mouse actions, file opening and closing.
It stores the current drawing in an array of Shape .

The Shape interface specifies the Shape type
The shape classes all implement Shape and represent different kinds of shapes.

Files:
A drawing is stored in a file containing one line for each shape,
Each line has the name of the type of shape,
followed by a specification of the shape,
including the colour (three integers for red, blue, and green)
and the position (x and y)
The other values will depend on the shape.

User Interface:
There are buttons for dealing with the whole drawing (New, Load, Save),
buttons for specifying the next shape to draw, and
buttons for moving and removing shapes, and setting the color.
 */

public class MiniDraw implements UIButtonListener, UIMouseListener{

    // Fields
    private ArrayList<Shape> shapes = new ArrayList<Shape>();    // the collection of shapes

    // suggested fields to store mouse positions, current action, current shape, current colour, etc

    private double pressedX;                 //where the mouse was pressed
    private double pressedY;  
    private String currentAction = "Line";   // current action ("Move", etc) or shape ("Line" etc)
    private Shape currentShape = null;      //  the current shape (or null if no shape)
    private Color currentColor = Color.red;
    private Polygon currentPolygon;          // [challenge] The polygon (if any) that is currently being constructed.

    /** Constructor sets up the GUI:
     *  sets the mouse listener and adds all the buttons
     */
    public MiniDraw(){
        /*# YOUR CODE HERE */ //list of buttons
        UI.addButton("New", this);
        UI.addButton("Open", this);
        UI.addButton("Save", this);
        UI.addButton("Rectangle", this);
        UI.addButton("Oval", this);
        UI.addButton("Line", this);
        UI.addButton("Dot", this);
        UI.addButton("Polygon",this);
        UI.addButton("Color", this);
        UI.addButton("Move", this);
        UI.addButton("Resize", this);
        UI.addButton("Delete", this);
        UI.setMouseListener(this);

    }

    /** Respond to button presses
     * For New, Open, Save, and Color, call the appropriate method (see below)
     *  to perform the action immediately.
     * For other buttons, store the button name in the currentAction field
     */
    public void buttonPerformed(String button){
        /*# YOUR CODE HERE */
         
           this.currentPolygon = null; //when a button is clicked it resets 
        if(button.equals("New")){
            this.newDrawing(); //calls newdrawing method
        }else if(button.equals("Open")){
            this.openDrawing();//calls opendrawing
        }else if(button.equals("Save")){
            this.saveDrawing();//call save drawing
        }else if(button.equals("Rectangle")){
            this.currentAction = button; //current action is rectangle
        }else if(button.equals("Oval")){
            this.currentAction = button; //current action is oval
        }else if(button.equals("Line")){
            this.currentAction = button; //current action is line
        }else if(button.equals("Dot")){
            this.currentAction = button; //current action is dot
        }else if(button.equals("Polygon")){
            this.currentAction = button; //current action is polygon
        }else if(button.equals("Color")){
            this.selectColor(); //calls select color method
        }else if(button.equals("Move")){
            this.currentAction = button; //current action is move
        }else if(button.equals("Resize")){
            this.currentAction = button; //current action is resize
        }else if(button.equals("Delete")){
            this.currentAction = button; //current action is delete
        }
    }

    // Respond to mouse events 

    /** When mouse is pressed, remember the position in fields
    and also find the shape it is on (if any), and store
    the shape in a field (use the findShape(..) method)
     *  When the Mouse is released, depending on the currentAction,
     *  - perform the action (move, delete, or resize).
     *    move and resize are done on the shape  where the mouse was pressed,
     *    delete is done on the shape where the mouse was released 
     *  - construct the shape and add to the shapes array.
     *    (though the polygon is more complicated)
     *  It is easiest to call other methods (see below) to actually do the work,
     *  otherwise this method gets too big!
     */
    public void mousePerformed(String mouseAction, double x, double y) {
        if (mouseAction.equals("pressed")){
            /*# YOUR CODE HERE */
            pressedX = x; //stores the x postion
            pressedY = y; //stores the y positon
            this.currentShape = this.findShape(x,y); //if clicked on a shape sets to current shape using findshape method
        }
        if (mouseAction.equals("released")){
            /*# YOUR CODE HERE */
            if(this.currentAction.equals("Move")){
                if(this.currentShape != null){
                    this.moveShape(x-pressedX,y-pressedY); //moves shape
                }
            }else if(this.currentAction.equals("Resize")){
                if(this.currentShape != null){
                    this.resizeShape(x-pressedX,y-pressedY); //resizies shape
                }
            }else if(this.currentAction.equals("Delete")){
                if(this.currentShape != null){
                    this.deleteShape(x,y); //deletes shape
                }
            }else if(this.currentAction.equals("Polygon")){
                this.addPolygon(x,y); //calls polygon method
            }else{
                if(this.currentAction.equals("Line")){
                    this.addShape(pressedX, pressedY,x ,y); //for line
                }else{
                    this.addShape(pressedX, pressedY,x-pressedX ,y-pressedY); //for rectangle and oval and dot
                }
            }
            this.drawDrawing(); //redraw the drawing 
        }
    }

    // -----------------------------------------------------
    // Methods that actually do the work  
    // -----------------------------------------------------

    /** Draws all the shapes in the list on the graphics pane
    First clears the graphics pane, then draws each shape,
    Finally repaints the graphics pane
     */
    public void drawDrawing(){
        UI.clearGraphics(false);
        /*# YOUR CODE HERE *///draw all the shapes
        for(Shape s: shapes){ 
            s.redraw(); //redraws the shapes in the list
        }
        UI.repaintGraphics();
    }   

    /** Checks each shape in the list to see if the point (x,y) is on the shape.
    It returns the topmost shape for which this is true.
    Returns null if there is no such shape.
     */
    public Shape findShape(double x, double y){
        /*# YOUR CODE HERE */
        Collections.reverse(shapes); //runs the shapes array backwards
        for(Shape s: shapes){
            if(s.on(x,y)){ //checks to see if x and y is on a shape if yes return the shape
                return s; 
            }
        }
        return null;  
    }

    /** Sets the current color.
     * Asks user for a new color using a JColorChooser (see MiniPaint, Assig 6)
     * As long as the color is not null, it remembers the color */
    private void selectColor(){
        /*# YOUR CODE HERE */
        if(this.currentColor != null){
            this.currentColor =  JColorChooser.showDialog(null, "Choose Color", null); //sets the color
        }
    }

    /** Start a new drawing -
    initialise the shapes array and clear the graphics pane. */
    public void newDrawing(){
        /*# YOUR CODE HERE */
        this.shapes.clear(); //clears the array
        UI.clearGraphics();
    }

    /** Construct a new Shape object of the appropriate kind
    (depending on currentAction) using the appropriate constructor
    of the Line, Rectangle, Oval, or Dot classes.
    Adds the shape to the end of the collection of shapes in the drawing, and
    Re-draws the drawing */
    public void addShape(double x1, double y1, double x2, double y2){
        Trace.printf("Drawing shape %s, at (%.2f, %.2f)-(%.2f, %.2f)\n",
            this.currentAction, x1, y1, x2, y2);  //for debugging
        /*# YOUR CODE HERE */
        if(this.currentAction.equals("Rectangle")){
            Rectangle rect = new  Rectangle(x1, y1, x2, y2, this.currentColor); //new rectangle object
            this.shapes.add(rect); //adds the rectangle to the shapes array

        }else if(this.currentAction.equals("Line")){
            Line line = new Line(x1, y1, x2, y2, this.currentColor); //new line object 
            this.shapes.add(line); //adds the line to the shapes array
        }else if(this.currentAction.equals("Oval")){
            Oval oval = new Oval(x1, y1, x2, y2, this.currentColor); //new oval object
            this.shapes.add(oval); //adds the oval to the shapes array
        }else if(this.currentAction.equals("Dot")){
            Dot dot = new Dot(x1, y1, this.currentColor); //new dot object
            this.shapes.add(dot); //adds a dot to the shapes array
        }
        this.drawDrawing(); //draws the drwaing
    }

    /** Moves the current shape (if there is one)
    to where the mouse was released.
    Ie, change its position by (toX-fromX) and (toY-fromY)
     */
    public void moveShape(double changeX, double changeY){
        Trace.printf("Moving shape by (%.2f, %.2f)\n", changeX, changeY);  //for debugging
        /*# YOUR CODE HERE */
        if(currentShape != null){
            this.currentShape.moveBy(changeX, changeY); //moves the current shape by the new x and y postions
        }

    }

    /** Finds the shape that was under the mouseReleased position (x, y)
    and then removes it from the array of shapes, moving later shapes down. 
     */
    public void deleteShape(double x, double y){
        Trace.printf("Deleting shape under (%.2f, %.2f)\n", x, y);  //for debugging
        //Find the index of the shape that (pressedX, pressedY) is on.
        //and remove the shape from the drawing, then draw the drawing again
        //If not pressed on any shape, then do nothing.
        /*# YOUR CODE HERE */
        Shape removed = this.findShape(x,y); //the selected shape to be removed 
        if(removed != null){
            int delete = shapes.indexOf(removed); //the index of a shape set to an int variable 
            shapes.remove(delete); //removes the shape from the array
        }else{return;}
        this.drawDrawing();
    }

    // METHODS FOR THE COMPLETION PART
    /** Resizes the current shape. A simple way of doing it is to
    resize the shape by the amount that the mouse was moved
    (ie from (fromX, fromY) to (toX, toY)). 
    If the mouse is moved to the right, the shape should
    be made that much wider on each side; if the mouse is moved to
    the left, the shape should be made that much narrower on each side
    If the mouse is moved up, the shape should be made
    that much higher top and bottom; if the mouse is moved down, the shape 
    should be made that much shorter top and bottom.
    The effect is that if the user drags from the top right corner of
    the shape, the shape should be resized to whereever the dragged to.
     */
    public void resizeShape(double changeX, double changeY){
        Trace.printf("Changing size of shape by (%.2f, %.2f) \n", changeX, changeY);  //for debugging
        /*# YOUR CODE HERE */
            if(currentShape != null){
                this.currentShape.resize(changeX, changeY); //resize the shape on the current shape
            }
    }

    /** Adds a polygon [challenge].
    If the currentPolygon is null, then create a new polygon with
    just the point x,y. Store it in currentPolygon, and add it to shapes.
    If the currentPolygon is not null, then add a new point to it.
    (Don't add it to shapes, since it is already there).
    Note, you need to reset currentPolygon to null every time a button is pressed*/
    
    public void addPolygon(double x, double y){
        /*# YOUR CODE HERE */
        if(currentPolygon == null){
           Polygon p = new Polygon(x,y, this.currentColor); //new polygon object
            this.currentPolygon = p; //current polygon is set to p
            this.shapes.add(p); //add the current polygon to the shapes array
        }else{
            this.currentPolygon.addPointX(x, y);
            this.currentPolygon.redraw();
            //add the points x and y to polygon array
        }
    }
     
    /** Ask the user to select a file and save the current drawing to the file. */
    public void saveDrawing(){
        /*# YOUR CODE HERE */
        try{
            PrintStream filesave = new PrintStream(new File(UIFileChooser.save()));
            for(Shape s: shapes){
                filesave.println(s.toString());
            }
            filesave.close();
        }catch(IOException e){UI.println("Fail: " + e);}
    }

    /**
     * Ask the user for a file to open,
     * then read all the shape descriptions into the current drawing.
     * For each line of the file, it will read the first token to find out which
     * kind of shape and read the rest of the line into a string.
     * It will then call the appropriate constructor, passing the string as an argument.
     */
    public void openDrawing(){
        /*# YOUR CODE HERE */
        UI.clearGraphics();
        newDrawing(); //calls the new drawing method
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open())); //new scanner to check the new file
            while(sc.hasNext()){ //while the scanner has something in it
                String shape = sc.next(); //what type of shape is in the file as a string
                if(shape.equals("Line")){
                    shapes.add(new Line(sc.nextLine())); //adds the line to the shapes array
                }
                if(shape.equals("Rectangle")){
                    shapes.add(new Rectangle(sc.nextLine())); //adds the rectange to the shapes array
                }
                if(shape.equals("Oval")){
                    shapes.add(new Oval(sc.nextLine())); //adds the oval to the shapes array
                }
                if(shape.equals("Dot")){
                    shapes.add(new Dot(sc.nextLine())); //adds any dots to the shapes array
                }
            }
        }catch(IOException e){UI.println("Fail: " + e);}
        drawDrawing();//draws the drawing
    }

    public static void main(String args[]){
        new MiniDraw();
    }

}

